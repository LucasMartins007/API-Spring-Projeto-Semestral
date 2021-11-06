/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.api.controller.impl;

import com.daki.api.controller.AbstractController;
import com.daki.api.controller.AuthenticationController;
import com.daki.domain.config.security.provider.AuthenticationToken;
import com.daki.domain.exception.enums.EnumDomainException;
import com.daki.domain.model.UserAuthentication;
import com.daki.domain.model.classes.CustomUserDetails;
import com.daki.domain.model.classes.TokenBean;
import com.daki.domain.model.classes.UserTransfer;
import com.daki.domain.service.UserDetailsService;
import com.daki.domain.util.StringUtil;
import com.daki.domain.util.TokenUtil;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;
    
    @Autowired
    private HttpServletRequest request;

    @Override
    public ResponseEntity<UserTransfer> authenticate(UserAuthentication user) {
        final CustomUserDetails userDetails = resolverLogin(user);

        final TokenBean tokenBean = TokenUtil.createToken(userDetails, null);

        final UserTransfer userTransfer = new UserTransfer(
                user.getEmail(),
                user.getSenha(),
                tokenBean
        );

        return ResponseEntity.ok(userTransfer);
    }

    private CustomUserDetails resolverLogin(UserAuthentication userAuthentication) {
        if (StringUtil.isNotNullOrEmpty(userAuthentication.getRefreshToken())) {
            if (!TokenUtil.validateToken(userAuthentication.getRefreshToken(), getOrigin())) {
                throw new BadCredentialsException(EnumDomainException.TOKEN_NOT_VALID.getMessage());
            }

        }
//        final String username = TokenUtil.getUsernameFromToken(userAuthentication.getRefreshToken());

        return userDetailsService.loadUserByUsername(userAuthentication.getEmail());

//        final String email = userAuthentication.getEmail();
//        final String senha = userAuthentication.getSenha();

//        final AuthenticationToken authenticationToken = new AuthenticationToken(email, senha);
//        final Authentication authentication = authenticationManager.authenticate(authenticationToken);

//        SecurityContextHolder.getContext().setAuthentication(authentication);

//        return (CustomUserDetails) authentication.getPrincipal();
    }
    
    protected String getOrigin() {
        return request.getHeader(HttpHeaders.ORIGIN);
    }
}
