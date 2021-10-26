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
public enum EnumStatusPedido implements IEnum<Integer> {

    CARRINHO(1, "Carrinho"),
    AGUARDANDO_RETIRADA(2, "Aguardando Retirada"),
    AGUARDANDO_PAGAMENTO(3, "Aguardando Pagamento"),
    CONFIRMADO(4, "Confirmado"),
    EM_TRANSITO(5, "Em transito"),
    CONCLUIDO(6, "Concluido"),
    CALCELADO(7, "Cancelado");

    private final Integer key;

    private final String value;

    private EnumStatusPedido(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumStatusPedido, Integer> {
    }

}
