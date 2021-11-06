/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.config.security.provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author lucas
 */
public class AuthenticationToken extends UsernamePasswordAuthenticationToken {
    
    public AuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
