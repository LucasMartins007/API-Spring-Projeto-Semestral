/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.service;

import com.daki.domain.model.classes.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author lucas
 */
public interface UserDetailsService {
    
    public CustomUserDetails loadUserByUsername(String string) throws UsernameNotFoundException;
    
}
