/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model.dto;

import com.daki.api.converter.TransientFieldDto;
import com.daki.domain.model.dto.system.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucas
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PessoaDto extends AbstractDto<Integer> {

    private static final long serialVersionUID = -5664201131943443401L;
    
    private Integer id;
    
    private String nome;
    
    private String cpf;
    
    private EmailDto email;

    @TransientFieldDto
    private String senha;
    
}
