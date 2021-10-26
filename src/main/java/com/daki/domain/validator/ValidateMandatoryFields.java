/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.validator;


import com.daki.domain.exception.DomainException;
import com.daki.domain.exception.enums.EnumDomainException;
import com.daki.domain.util.ListUtil;
import com.daki.domain.util.NumericUtil;
import com.daki.domain.util.StringUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author lucas
 */
public class ValidateMandatoryFields {
    private final List<String> listNull = new ArrayList<>();

    public void add(Object valor, String nome) {
        boolean isNullOrEmpty;
        if (valor instanceof Collection<?>) {
            isNullOrEmpty = ListUtil.isNullOrEmpty((Collection<?>) valor);
        } else if (valor instanceof CharSequence) {
            isNullOrEmpty = StringUtil.isNullOrEmpty((CharSequence) valor);
        } else if (valor != null && valor.getClass().isArray()) {
            isNullOrEmpty = ListUtil.isNullOrEmpty((Object[]) valor);
        } else {
            isNullOrEmpty = valor == null;
        }

        if (isNullOrEmpty) {
            listNull.add(nome);
        }
    }

    public void addValor(Number valor, String nome) {
        if (valor == null || NumericUtil.isLessOrEquals(valor, 0)) {
            listNull.add(nome);
        }
    }

    public void validate() {
        String mensagem = getMessageErrors();
        if (mensagem != null) {
            throw new DomainException(EnumDomainException.CAMPOS_OBRIGATORIOS.getMessage(), mensagem);
        }
    }

    public String getMessageErrors() {
        if (ListUtil.isNotNullOrEmpty(this.listNull)) {
            StringBuilder message = new StringBuilder();
            for (String nullField : this.listNull) {
                message.append(nullField).append("<br/>");
            }
            return message.toString();
        }
        return null;
    }


}
