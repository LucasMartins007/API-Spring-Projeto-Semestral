package com.daki.domain.model.enums;

import com.daki.domain.model.enums.interfaces.IEnum;
import com.daki.domain.model.enums.converter.AbstractEnumConverter;
import javax.persistence.Converter;

/**
 *
 * @author lucas
 */
public enum EnumTipoPessoa implements IEnum<String> {
    
    PES_CLIENTE("C", "Cliente"),
    PES_GERENTE("G", "Gerente"),
    PES_FUNCIONARIO("F", "Funcionario");
    
    private final String value;
    
    private final String key;

    private EnumTipoPessoa(String value, String key) {
        this.value = value;
        this.key = key;
    }

    @Override
    public String getValue() {
        return value;
    }
    
    @Override
    public String getKey() {
        return key;
    }
    
    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumTipoPessoa, String> {
    }
}
