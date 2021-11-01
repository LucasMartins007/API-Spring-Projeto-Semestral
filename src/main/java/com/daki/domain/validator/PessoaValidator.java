/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.validator;

import com.daki.domain.exception.DomainException;
import com.daki.domain.exception.enums.EnumDomainException;
import com.daki.domain.model.Email;
import com.daki.domain.model.Pessoa;
import com.daki.domain.util.CpfCnpjValidator;
import com.daki.domain.util.ListUtil;
import com.daki.domain.util.StringUtil;
import com.daki.domain.util.ValidateEntity;
import com.daki.persistence.repository.PessoaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucas
 */
@Component
public class PessoaValidator extends AbstractValidatorImpl<Pessoa> {

    private static final String CAMPO_NOME = "nome";
    private static final String CAMPO_CPF = "cpf";
    private static final String CAMPO_SENHA = "senha";
    private static final String CAMPO_EMAIL = "email";

    @Override
    public void validateRequiredFields(Pessoa pessoa) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();

        validate.add(pessoa.getNome(), CAMPO_NOME);
        validate.add(pessoa.getCpf(), CAMPO_CPF);
        validate.add(pessoa.getEmails(), CAMPO_EMAIL);
        validate.add(pessoa.getSenha(), CAMPO_SENHA);
        validate.validate();
    }

    @Override
    public void validateInsert(Pessoa pessoa) {
        validateRequiredFields(pessoa);
        validateSizeFields(pessoa);
        validateFormatFields(pessoa);
    }

    @Override
    public void validateFormatFields(Pessoa pessoa) {
        validarNome(pessoa);
        validarSenha(pessoa);
        validarCpf(pessoa);
        validarEmailPessoa(pessoa);
    }

    @Override
    public void validateSizeFields(Pessoa pessoa) {
        ValidateEntity.validateMaxCaracter(pessoa.getNome(), 50, CAMPO_NOME);
        ValidateEntity.validateMaxCaracter(pessoa.getSenha(), 20, CAMPO_SENHA);
    }

    public void validarSenha(Pessoa pessoa) {
        if (!pessoa.getSenha().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
            throw new DomainException(EnumDomainException.CAMPO_INVALIDO.getMessage(), CAMPO_SENHA);
        }
    }

    public void validarNome(Pessoa pessoa) {
        if (!pessoa.getNome().matches("[a-zA-Z][a-zA-Z ]*")) {
            throw new DomainException(EnumDomainException.CAMPO_INVALIDO.getMessage(), CAMPO_NOME);
        }
    }

    public void validarEmailPessoa(Pessoa pessoa) {
        List<Pessoa> pessoas = new ArrayList<>();
        Email managedEmail = ListUtil.first(pessoa.getEmails());

        for (Email email : pessoa.getEmails()) {
            if (!StringUtil.isValidEmail(email.getEmail())) {
                throw new DomainException(EnumDomainException.EMAIL_INVALIDO.getMessage(), email.getEmail());
            }

            getRepository(PessoaRepository.class)
                    .findAllByEmails(managedEmail.getEmail())
                    .stream()
                    .forEach(pessoas::add);

            if (ListUtil.isNotNullOrEmpty(pessoas)) {
                throw new DomainException(EnumDomainException.EMAIL_DUPLICADO.getMessage(), managedEmail.getEmail());
            }

        }
    }

    public void validarCpf(Pessoa pessoa) {
        CpfCnpjValidator.validateCpf(pessoa.getCpf());

        List<Pessoa> pessoas = new ArrayList<>();
        getRepository(PessoaRepository.class)
                .findAllByCpf(pessoa.getCpf())
                .stream()
                .forEach(pessoas::add);

        if (ListUtil.isNotNullOrEmpty(pessoas)) {
            throw new DomainException(EnumDomainException.CPF_DUPLICADO.getMessage(), pessoa.getCpf());
        }
    }

}
