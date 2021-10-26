/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class UserAuthentication {
    
    private String token;
    
    private String email;
    
    private String senha;
    
}
