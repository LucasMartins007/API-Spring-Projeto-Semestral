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
public enum EnumBandeiraCartao implements IEnum<String>{
    
    VISA("Cartão Visa"),
    MASTER_CARD("Cartão Mastercard"),
    AMERICAN_EXPRESS("Cartão American Express"),
    HIPER_CARD("Cartão Hipercard"),
    ELO("Cartão ELO");
    
    private final String key;

    private EnumBandeiraCartao(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumBandeiraCartao, String> {
    }
    
    
}
