/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.api.controller;

import javax.servlet.http.HttpServletRequest;

import com.daki.api.converter.Converter;
import com.daki.domain.model.AbstractEntity;
import com.daki.domain.model.dto.system.AbstractDto;
import com.daki.domain.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * @author lucas
 * @param <E> Classe de serviço padrão da controller
 */
@Component
public abstract class AbstractController<E extends IAbstractService> {

    @Autowired
    private HttpServletRequest request;
    
    private final E serviceClass;


    public AbstractController(E serviceClass) {
        this.serviceClass = serviceClass;
    }
    

    protected E getService() {
        return serviceClass;
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
    
    protected String getOrigin() {
        return request.getHeader(HttpHeaders.ORIGIN);
    }

    protected <D extends AbstractDto<?>, E extends AbstractEntity> E converterDTOParaEntity(D dto, Class<E> clazzEntity) {
        return Converter.converterDTOParaEntity(dto, clazzEntity);
    }

    protected <D extends AbstractDto<?>, E extends AbstractEntity> List<E> converterDTOParaEntity(List<D> dtos, Class<E> clazzEntity) {
        return Converter.converterDTOParaEntity(dtos, clazzEntity);
    }

    protected <D extends AbstractDto<?>, E extends AbstractEntity> D converterEntityParaDTO(E entity, Class<D> clazzDto) {
        return Converter.converterEntityParaDTO(entity, clazzDto);
    }

    protected <D extends AbstractDto<?>, E extends AbstractEntity> List<D> converterEntityParaDTO(List<E> entitys, Class<D> clazzDto) {
        return Converter.converterEntityParaDTO(entitys, clazzDto);
    }
}
