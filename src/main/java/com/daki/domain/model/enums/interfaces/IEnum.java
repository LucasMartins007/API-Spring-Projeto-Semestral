/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model.enums.interfaces;

/**
 *
 * @author lucas
 */
public interface IEnum<E> {
    
    E getKey();

    String getValue();

    default String getName() {
        return ((Enum) this).name();
    }


}
