/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daki.api.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucas
 * @param <D> Classe de DTO
 * @param <E> Classe de entidade
 */
@Component
public abstract class AbstractConverter<D, E> implements IConverter<D, E>{
    
    ModelMapper mapper;

    public AbstractConverter(ModelMapper mapper) {
        ModelMapper map = new ModelMapper();
        this.mapper = map;
    }
    
}
