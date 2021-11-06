/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.service;

import com.daki.domain.model.Pessoa;
import com.daki.domain.model.dto.PessoaDto;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public interface PessoaService extends IAbstractService<Pessoa, Integer, PessoaDto>{
    
    public Pessoa salvarPessoa(Pessoa pessoa);

}
