/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.api.controller.impl;

import com.daki.api.controller.AbstractController;
import com.daki.api.controller.AuthenticationController;
import com.daki.domain.model.UserAuthentication;
import com.daki.domain.service.AuthenticationService;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
public class AuthenticationControllerImpl  implements AuthenticationController{

//    public AuthenticationControllerImpl(Class<AuthenticationService> serviceClass) {
//        super(serviceClass);
//    }

    @Override
    public UserAuthentication authenticate(UserAuthentication user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
