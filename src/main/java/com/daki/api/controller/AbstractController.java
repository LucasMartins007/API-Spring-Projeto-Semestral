/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.api.controller;

import com.daki.persistence.pattern.CDIProviderFactory;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucas
 * @param <S> Classe de serviço padrão da controller
 * @param <C> Classe de conversão padrão da controller
 */
@Component
public abstract class AbstractController<S, C> {

    @Autowired
    private HttpServletRequest request;
    
    private Class<S> serviceClass;
    
    private Class<C> converterClass;

    protected  S getServiceClass() {
        return CDIProviderFactory.getCDIProvider().getReference(serviceClass);
    }
    
    protected C getConverterClass(){
        return CDIProviderFactory.getCDIProvider().getReference(converterClass);
    }
    
    protected ResponseEntity okResponse() {
        return ResponseEntity.ok().build();
    }

    protected ResponseEntity okResponse(Object body) {
        return ResponseEntity.ok(body);
    }
    
    protected ResponseEntity createCreatedResponse(Object body){
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
    
    protected <T> ResponseEntity<T> createBadRequestResponse(T body) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
    
    protected ResponseEntity createNoContentResponse() {
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity createBadRequestResponse() {
        return ResponseEntity.badRequest().build();
    }
    
    protected String getHeader(String name) {
        return request.getHeader(name);
    }
}
