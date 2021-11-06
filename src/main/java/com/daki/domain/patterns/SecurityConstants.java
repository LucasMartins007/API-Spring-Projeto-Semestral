/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.patterns;

import org.springframework.http.HttpHeaders;

/**
 *
 * @author lucas
 */
public class SecurityConstants {

    public static final String SECRET_KEY = "FGj2herA5v2ZUaTt";

    public static final long EXPIRATION_TIME = 1000L * 60 * 60;

    public static final String BEARER_SCHEME = "Bearer ";

    public static final String BASIC_SCHEME = "Basic ";

    public static final String HEADER_TOKEN = HttpHeaders.AUTHORIZATION;

}
