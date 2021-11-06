/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.patterns.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author lucas
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermitRole {

    /**
     * Retorna a lista de atributos de configuração de segurança
     * validação com base no usuario do token,
     * quando seu usuario é ROLE_ADMIN, não necessita da anotação @PermitRole
     * pois esse é o super usuario.
     * <p>
     * Entidade = Role
     * possui metodos estáticos
     * para uso.
     * <p>
     * exemplos = ROLE_ROOT, ROLE_ADMIN, ROLE_USER, ROLE_RECEPTIONIST, ROLE_PERSONAL, ROLE_ANONYMOUS"
     *
     * @return String[] Os atributos do método seguro
     */
    String[] value() default "";

    String pathId() default "";

}
