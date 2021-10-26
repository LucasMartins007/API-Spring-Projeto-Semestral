/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.exception.enums;

/**
 *
 * @author lucas
 */
public enum EnumDomainException {
 
    CAMPOS_OBRIGATORIOS("Os seguintes campos são de preenchimento obrigatório:</br>{0}");
    
    
    private final String message;

    private EnumDomainException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
