/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daki.api.controller.converter;

import org.modelmapper.ModelMapper;

/**
 *
 * @author lucas
 * @param <D> Classe de DTO
 * @param <E> Classe de entidade
 */
public abstract class AbstractConverter<D, E> implements IConverter<D, E>{
    
    final protected ModelMapper mapper;

    public AbstractConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }
    
}