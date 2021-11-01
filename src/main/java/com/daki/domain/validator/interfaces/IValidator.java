/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daki.domain.validator.interfaces;

/**
 *
 * @author lucas
 */
public interface IValidator<E> {
    
    void validateRequiredFields(E entity);
    
    default void validateDelete(E entity) {
    }

    default void validateUpdate(E entity) {
    }

    default void validateInsert(E entity) {
    }

    default void validateInsertOrUpdate(E entity) {
    }

    default void validateSizeFields(E entity) {
    }
    
    default void validateFormatFields(E entity){
    }

}
