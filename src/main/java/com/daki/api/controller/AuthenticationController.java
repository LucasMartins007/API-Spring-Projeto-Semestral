/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.api.controller;

import com.daki.domain.model.UserAuthentication;
import com.daki.domain.model.classes.UserTransfer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
@Api(tags = AuthenticationController.TAGS)
public interface AuthenticationController {
    
    final String PATH = "/login";
    
    final String TAGS = "Login";
    
    @PostMapping(AuthenticationController.PATH)
    ResponseEntity<UserTransfer> authenticate(@RequestBody UserAuthentication user);
}
