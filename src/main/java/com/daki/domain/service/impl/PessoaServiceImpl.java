/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.service.impl;

import com.daki.domain.model.Pessoa;
import com.daki.domain.service.AbstractService;
import com.daki.domain.service.PessoaService;
import com.daki.persistence.repository.ProdutoRepository;
import com.daki.persistence.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lucas
 */
public class PessoaServiceImpl extends AbstractService<Pessoa, Integer> implements PessoaService {
    
    @Override
    public Pessoa salvarPessoa(Pessoa pessoa) {
          
        
        getRepository(TelefoneRepository.class).findAll();
        getRepository().findAll();
        getRepository(ProdutoRepository.class).deleteAll();;
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
