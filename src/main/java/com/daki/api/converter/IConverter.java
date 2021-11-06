/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daki.api.converter;

/**
 *
 * @author lucas
 * @param <D> Classe de DTO
 * @param <E> Classe de Entidade
 */
public interface IConverter<D, E> {
    
    D convertToDto(E clazzEntity);
    
    E convertToEntity(D clazzDto);
    
}
