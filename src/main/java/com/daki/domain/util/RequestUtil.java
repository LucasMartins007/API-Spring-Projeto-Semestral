/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.util;

import com.daki.domain.exception.DomainException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author lucas
 */
public class RequestUtil {
    
    public static Map extractPathVariableToMap(HttpServletRequest request) {
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (Objects.nonNull(pathVariables)) {
            return pathVariables;
        }
        return null;
    }

    public static Map extractQueryParamVariableToMap(final HttpServletRequest request) {
        final Enumeration enumeration = request.getParameterNames();
        final Map<String, Object> modelMap = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String parameterName = (String) enumeration.nextElement();
            modelMap.put(parameterName, request.getParameter(parameterName));
        }
        return modelMap;
    }

    public static Integer getParamValueAsInteger(HttpServletRequest request, String parametro) {
        Map pathVariables = extractPathVariableToMap(request);
        if (pathVariables != null) {
            return NumericUtil.parseInt(pathVariables.get(parametro));
        }
        return null;
    }

    public static String resolverUrlControllerWithTokenQueryParam(final HttpServletRequest request, final String path) {
        final Map<String, String> urlParams = new HashMap<>();

//        urlParams.put("token", SendEmailBean.TOKEN_REPLACE_KEY);

        return resolverUrlController(request, path, urlParams);
    }

    public static String resolverUrlController(final HttpServletRequest request, final String path, final Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();

        builder.scheme(request.getScheme());

        if (request.getServerName().contains("localhost")) {
            builder.host(request.getRemoteHost())
                    .port(request.getServerPort());
        } else {
            builder.host(request.getServerName());
        }

        builder.path(path);

        if (queryParams != null && queryParams.size() > 0) {
            queryParams.forEach(builder::queryParam);
        }

        return builder.toUriString();
    }

    /**
     * @param sort query param
     * @apiNote sort = id:desc
     */
    public static Sort resolveSort(String sort) {
        try {
            Sort sortedBy = Sort.unsorted();

            if (StringUtil.isNotNullOrEmpty(sort)) {
                final String[] split = sort.split(":");

                if (split.length > 1) {
                    sortedBy = Sort.by(Sort.Direction.fromString(split[1]), split[0]);
                } else {
                    sortedBy = Sort.by(split[0]);
                }
            }

            return sortedBy;
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }
}
