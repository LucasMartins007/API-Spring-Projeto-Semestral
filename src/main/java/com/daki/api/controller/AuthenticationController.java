/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.api.controller;

import com.daki.domain.model.Pessoa;
import com.daki.domain.model.UserAuthentication;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(value = AuthenticationController.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = AuthenticationController.TAGS)
public interface AuthenticationController {
    
    final String PATH = "login/";
    
    final String TAGS = "Login";
    
    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    UserAuthentication authenticate(UserAuthentication user);
}
