/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.service.impl;

import com.daki.domain.model.Pessoa;
import com.daki.domain.service.AbstractService;
import com.daki.domain.service.PessoaService;
import com.daki.domain.util.DateUtil;
import com.daki.domain.util.DomainUtil;
import com.daki.domain.validator.PessoaValidator;
import com.daki.persistence.repository.PessoaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class PessoaServiceImpl extends AbstractService<Pessoa, Integer> implements PessoaService {
    
    private final PessoaValidator pessoaValidator;

    public PessoaServiceImpl(PessoaValidator pessoaValidator) {
        this.pessoaValidator = pessoaValidator;
    }
    
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
