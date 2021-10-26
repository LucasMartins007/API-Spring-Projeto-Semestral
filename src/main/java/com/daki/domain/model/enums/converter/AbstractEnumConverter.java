/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model.enums.converter;

import com.daki.domain.model.enums.interfaces.IEnum;
import java.lang.reflect.ParameterizedType;
import javax.persistence.AttributeConverter;

/**
 *
 * @author lucas
 * @param <T> Enum a ser convertida em E
 * @param <E> Tipo de dado que T será convertido
 */
public class AbstractEnumConverter<T extends Enum<T> & IEnum<E>, E> implements AttributeConverter<T, E> {
 
    private final Class<T> clazz;

    public AbstractEnumConverter() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public E convertToDatabaseColumn(T attribute) {
        return attribute != null ? attribute.getKey() : null;
    }

    @Override
    public T convertToEntityAttribute(E dbData) {
        T[] enums = clazz.getEnumConstants();

        for (T e : enums) {
            if (e.getKey().equals(dbData)) {
                return e;
            }
        }

        return null;
    }

}
