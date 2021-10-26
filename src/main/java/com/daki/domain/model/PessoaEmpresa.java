/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucas
 */
@Getter
@Setter
@Entity
@SequenceGenerator(name = "seq_pessoa_empresa", sequenceName = "seq_pessoa_empresa")
public class PessoaEmpresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_pessoa_empresa")
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoa", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_pessoa_pessoa_empresa"))
    private Pessoa pessoa;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_empresa_pessoa_empresa"))
    private Empresa empresa;
}
