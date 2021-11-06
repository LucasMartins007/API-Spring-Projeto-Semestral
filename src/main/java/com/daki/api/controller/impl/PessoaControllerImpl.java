/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.api.controller.impl;

import com.daki.api.controller.AbstractController;
import com.daki.api.controller.PessoaController;
import com.daki.api.converter.Converter;
import com.daki.api.converter.PessoaConverter;
import com.daki.domain.model.Pessoa;
import com.daki.domain.model.dto.PessoaAtualizacaoDto;
import com.daki.domain.model.dto.PessoaDto;
import com.daki.domain.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
public class PessoaControllerImpl extends AbstractController<PessoaService> implements PessoaController {

    public PessoaControllerImpl(PessoaService serviceClass) {
        super(serviceClass);
    }
    
    @Override
    public ResponseEntity<PessoaDto> salvar(PessoaDto pessoaDto) {
        Pessoa pessoa = converterDTOParaEntity(pessoaDto, Pessoa.class);
        getService().salvarPessoa(pessoa);

        PessoaDto dto = converterEntityParaDTO(pessoa, PessoaDto.class);

        return  ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }


}
