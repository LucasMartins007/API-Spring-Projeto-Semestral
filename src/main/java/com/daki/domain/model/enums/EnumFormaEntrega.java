/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model.enums;

import com.daki.domain.model.enums.converter.AbstractEnumConverter;
import com.daki.domain.model.enums.interfaces.IEnum;
import javax.persistence.Converter;

/**
 *
 * @author lucas
 */
public enum EnumFormaEntrega implements IEnum<String> {

    RETIRAR("Retirar na loja"),
    RECEBER("Receber no endereco")
    ;
    
    private String key;

    private EnumFormaEntrega(String key) {
        this.key = key;
    }
    
    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumFormaEntrega, String> {
    }
}
