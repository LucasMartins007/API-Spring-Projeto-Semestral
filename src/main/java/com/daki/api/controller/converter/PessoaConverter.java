/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.api.controller.converter;

import com.daki.domain.model.Pessoa;
import com.daki.domain.model.dto.PessoaDto;
import org.modelmapper.ModelMapper;

/**
 *
 * @author lucas
 */
public class PessoaConverter extends AbstractConverter<PessoaDto, Pessoa>{

    public PessoaConverter(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public PessoaDto convertToDto(Pessoa pessoa) {
        return mapper.map(pessoa, PessoaDto.class);
    }

    @Override
    public Pessoa convertToEntity(PessoaDto pessoaDto) {
        return mapper.map(pessoaDto, Pessoa.class);
    }
    
}
