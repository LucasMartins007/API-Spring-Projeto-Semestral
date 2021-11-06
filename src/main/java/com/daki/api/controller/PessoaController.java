/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daki.api.controller;

import com.daki.domain.model.dto.PessoaDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(value = PessoaController.PATH)
@Api(tags = PessoaController.TAG)
public interface PessoaController {
    
    final String PATH = "pessoa/";
    
    final String TAG = "Pessoa";
    
    @PostMapping("salvar")
    @ApiOperation(value = "Salvar um cliente", code = 201, tags = "Pessoa")
    public ResponseEntity<PessoaDto> salvar(@RequestBody PessoaDto pessoaDto);
}
