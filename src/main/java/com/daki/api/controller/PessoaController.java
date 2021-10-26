/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daki.api.controller;

import com.daki.domain.model.Pessoa;
import com.daki.domain.model.dto.PessoaDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(name = PessoaController.PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = PessoaController.TAG, consumes = MediaType.APPLICATION_JSON_VALUE)
public interface PessoaController{
    
    final String PATH = "pessoa/";
    
    final String TAG = "Pessoa";
    
    @PostMapping("salvar/")
    @ApiOperation(value = "Salvar um cliente", code = 201)
    public ResponseEntity<PessoaDto> salvar(PessoaDto pessoaDto);
}
