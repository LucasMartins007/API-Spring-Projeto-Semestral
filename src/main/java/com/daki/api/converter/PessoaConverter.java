/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.api.converter;

import com.daki.domain.model.Email;
import com.daki.domain.model.Pessoa;
import com.daki.domain.model.dto.EmailDto;
import com.daki.domain.model.dto.PessoaDto;
import com.daki.domain.util.ListUtil;
import com.daki.domain.util.Utils;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucas
 */
@Component
public class PessoaConverter {

    
    public PessoaDto convertToDto(Pessoa pessoa) {
        if(!Utils.isEmpty(pessoa)){
            PessoaDto pessoaDto = new PessoaDto();
            pessoaDto.setId(pessoa.getId());
            pessoaDto.setNome(pessoa.getNome());
            pessoaDto.setCpf(pessoa.getCpf());
            
            EmailDto emailDto = new EmailDto();     
            if(ListUtil.isNotNullOrEmpty(pessoa.getEmails())){
                Email email = ListUtil.first(pessoa.getEmails());

                if(email != null){
                    emailDto.setId(email.getId());
                    emailDto.setEmail(email.getEmail());
                    emailDto.setAtivo(true);
                }
            }
            pessoaDto.setEmail(emailDto);
            return pessoaDto;
        }
        return null;
    }

    
    public Pessoa convertToEntity(PessoaDto pessoaDto) {
        if(pessoaDto != null){
            Pessoa pessoa = new Pessoa();
            pessoa.setAtivo(true);
            pessoa.setNome(pessoaDto.getNome());
            pessoa.setCpf(pessoaDto.getCpf());
            pessoa.setSenha(pessoaDto.getSenha());

            Email email = new Email();
            EmailDto emailDto = pessoaDto.getEmail();
            if(emailDto != null){
                email.setEmail(emailDto.getEmail());
                email.setAtivo(emailDto.isAtivo());
                email.setPessoa(pessoa);
            }
            
            pessoa.setEmails(new ArrayList<>());
            pessoa.getEmails().add(email);
            return pessoa;
        }
        return null;
    }
    
}
