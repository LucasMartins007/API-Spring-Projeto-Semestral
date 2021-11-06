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
 
    TOKEN_NOT_VALID("Acesso expirado! Por favor, acesse novamente o sistema."),
    ROLE_ACCESS_DENIED("Sua permissão de usuário proíbe realizar esta operação."),
    USERNAME_REQUIRED("Obrigatório informar o usuário."),
    LOGIN_NOT_FOUND("Usuário ou senha inválidos!"),
    CAMPOS_OBRIGATORIOS("Os seguintes campos são de preenchimento obrigatório:</br>{0}"),
    CAMPO_INVALIDO("O campo {0} foi preenchido de forma inválida, tente novamente. "),
    
    CAMPO_MAIOR_QUE("O campo {0} deve conter um valor maior que {1}."),
    CAMPO_MENOR_QUE("O campo {0} deve conter um valor menor que {1}."),
    CAMPO_ENTRE("O campo {0} deve conter um valor entre {1} e {2}."),
    CAMPO_MAIOR_OU_IGUAL_QUE("O campo {0} deve conter um valor maior ou igual a {1}."),
    CAMPO_MENOR_OU_IGUAL_QUE("O campo {0} deve conter um valor menor ou igual a {1}."),
    CAMPO_MENOR_IGUAL_ZERO("O campo {0} deve conter um valor maior que zero."),
    CAMPO_MINIMO_CARACTERS("O campo {0} deve conter no mínimo {1} caracteres."),
    CAMPO_MAXIMO_CARACTERS("O campo {0} deve conter no máximo {1} caracteres."),
    CAMPO_IGUAL_CARACTERS("O campo {0} deve conter exatamente {1} caracteres."),
    CAMPO_SOMENTE_NUMEROS("O campo {0} permite somente números."),
    CAMPO_SOMENTE_BOOLEAN("O campo {0} permite somente valores booleanos."),
    DATA_INICIAL_MAIOR_DATA_FINAL("A data inicial {0} não pode ser maior que a data final {1}."),
    DATA_MAIOR_DATA_ATUAL("A {0} {1} não pode ser maior que a data atual."),
    DATA_MENOR_DATA_ATUAL("A {0} {1} não pode ser menor que a data atual."),
    HORA_INICIAL_MAIOR_DATA_FINAL("A hora inicial {0} não pode ser maior que a hora final {1}."),
    
    
    CPF_INVALIDO("O CPF {0} é inválido."),
    CNPJ_INVALIDO("O CNPJ {0} é inválido."),
    EMAIL_INVALIDO("O email {0} é inválido."),
    EMAIL_DUPLICADO("O email {0} já existe, faça o login."), 
    CPF_DUPLICADO("O cpf {0} já existe, faça o login."), 
    
    
    ;
    
    
    
    private final String message;

    private EnumDomainException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
