/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.config.security;

import com.daki.domain.exception.enums.EnumDomainException;
import com.daki.domain.model.classes.CustomUserDetails;
import com.daki.domain.model.generic.Role;
import com.daki.domain.patterns.SecurityConstants;
import com.daki.domain.patterns.annotations.PermitRole;
import com.daki.domain.service.impl.GestaoUserDetailsServiceImpl;
import com.daki.domain.util.ClassUtil;
import com.daki.domain.util.ListUtil;
import com.daki.domain.util.RequestUtil;
import com.daki.domain.util.StringUtil;
import com.daki.domain.util.TokenUtil;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author lucas
 */
public class AuthorizationHandler implements HandlerInterceptor {
    @Autowired
    private GestaoUserDetailsServiceImpl userDetailsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) return false;

        if (shouldNotFilter(request)) return true;

        final Method method = ClassUtil.parseHandlerToMethod(handler);

        if (!TokenUtil.validateTokenFromRequest(request)) {
            throw new BadCredentialsException(EnumDomainException.TOKEN_NOT_VALID.getMessage());
        }

        final String token = request.getHeader(SecurityConstants.HEADER_TOKEN);
        final String username = TokenUtil.getUsernameFromToken(token);

        

        if (username != null) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(username);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            validateSecurePermitRole(authentication, request, handler);

            return true;
        }

        throw new BadCredentialsException("Please again login or contact service provider.");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        SecurityContextHolder.clearContext();
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    private void validateSecurePermitRole(Authentication authentication, HttpServletRequest request, Object handler) {
        final boolean isAdmin = request.isUserInRole(Role.ADMIN);
        if (!isAdmin) {
            final PermitRole permitRole = parseHandlerToMethod(handler);
            if (permitRole == null) {
                throw new AccessDeniedException(EnumDomainException.ROLE_ACCESS_DENIED.getMessage());
            }

            final List<GrantedAuthority> attributes = extractAttributes(permitRole);
            if (ListUtil.isNotNullOrEmpty(attributes)) {
                final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                final boolean hasRole = authorities.stream().anyMatch(e -> attributes.contains(e));
                if (!hasRole) {
                    throw new AccessDeniedException(EnumDomainException.ROLE_ACCESS_DENIED.getMessage());
                }
            }

            final boolean isPersonal = request.isUserInRole(Role.PERSONAL);
            if (isPersonal && StringUtil.isNotNullOrEmpty(permitRole.pathId())) {
                final CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
                final Integer idProfissional = RequestUtil.getParamValueAsInteger(request, permitRole.pathId());
                if (!userPrincipal.getUsuario().getProfessionalId().equals(idProfissional)) {
                    throw new AccessDeniedException(EnumDomainException.ROLE_ACCESS_DENIED.getMessage());
                }
            }
        }
    }

    private List<GrantedAuthority> extractAttributes(PermitRole permitRole) {
        return Stream.of(permitRole.value())
                .filter(StringUtil::isNotNullOrEmpty)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private PermitRole parseHandlerToMethod(Object handler) {
        final HandlerMethod handlerMethod = (HandlerMethod) handler;

        final Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(PermitRole.class)) {
            return method.getDeclaredAnnotation(PermitRole.class);
        }

        final Class<?> aClass = handlerMethod.getBeanType();
        if (aClass.isAnnotationPresent(PermitRole.class)) {
            return aClass.getDeclaredAnnotation(PermitRole.class);
        }

        return null;
    }

    private boolean shouldNotFilter(HttpServletRequest request) {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        return new AntPathMatcher().match("/login", request.getRequestURI());
    }
}
