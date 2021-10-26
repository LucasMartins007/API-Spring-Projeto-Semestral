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
public enum EnumSexo implements IEnum<String>{
    
    MASCULINO("M", "Masculino"),
    FEMININO("F", "Feminino"),
    OUTROS("O", "Prefiro não Informar");
    
    private final String value;
    
    private final String key;

    private EnumSexo(String value, String key) {
        this.value = value;
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }
    
    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumSexo, String> {
    }
}
