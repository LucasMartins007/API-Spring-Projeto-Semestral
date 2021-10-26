/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model.dto;

import com.daki.domain.model.Email;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class PessoaDto {
    
    private Integer id;
    
    private String nome;
    
    private String cpf;
    
    private Email email;
    
    private String senha;
    
}
