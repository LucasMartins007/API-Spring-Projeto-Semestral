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
public enum EnumTipoPagamento implements IEnum<Integer> {

    DINHEIRO(1, "Dinheiro", false),
    BOLETO(2, "Dinheiro", false),
    CARTAO_CREDITO(3, "Cartão de crédito", true),
    CARTAO_DEBITO(4, "Cartão de débito", false),
    PIX(5, "Pix", false);

    private final Integer key;

    private final String value;

    private final boolean parcelamento;

    private EnumTipoPagamento(Integer key, String value, boolean parcelamento) {
        this.key = key;
        this.value = value;
        this.parcelamento = parcelamento;
    }

    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public boolean isParcelamento() {
        return this.parcelamento;
    }
    
    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumTipoPagamento, Integer> {
    }

}
