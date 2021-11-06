/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.service;

import com.daki.domain.model.Pessoa;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public interface PessoaService {
    
    public Pessoa salvarPessoa(Pessoa pessoa);

}
