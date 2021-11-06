/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.service.impl;

import com.daki.domain.model.Pessoa;
import com.daki.domain.model.dto.PessoaDto;
import com.daki.domain.service.AbstractServiceImpl;
import com.daki.domain.service.PessoaService;
import com.daki.domain.util.DateUtil;
import com.daki.domain.util.DomainUtil;
import com.daki.domain.validator.PessoaValidator;
import com.daki.persistence.repository.PessoaRepository;
import com.google.common.util.concurrent.AbstractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
@RequiredArgsConstructor
public class PessoaServiceImpl extends AbstractServiceImpl<Pessoa, Integer, PessoaDto> implements PessoaService {
    
    private final PessoaValidator pessoaValidator;

    
    @Override
    public Pessoa salvarPessoa(Pessoa pessoa) {
        onPrepareInsert(pessoa);
        return getRepository().save(pessoa);
    }


    public void onPrepareUpdate(Pessoa pessoa, Pessoa managedPessoa){
    }



    public void onPrepareInsert(Pessoa pessoa){
        pessoaValidator.validateInsert(pessoa);
        pessoa.setDataInclusao(DateUtil.getDate());
        pessoa.setDataAlteracao(DateUtil.getDate());
        pessoa.setSenha(DomainUtil.criptografaSenha(pessoa.getSenha()));
        pessoa.setAtivo(true);
        
        pessoa.setNome(pessoa.getNome().toUpperCase());
        pessoa.getEmails().forEach(email -> email.setEmail(email.getEmail().toUpperCase()));
    }
    
}
