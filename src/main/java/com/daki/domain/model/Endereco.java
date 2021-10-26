/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucas
 */
@Getter
@Setter
@Entity
@Table(name = "pessoa_endereco")
@SequenceGenerator(name = "seq_pessoa_endereco", sequenceName = "seq_pessoa_endereco")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_endereco")
    private Long id;
    
    @Column(name = "cep")
    private String cep;
    
    @Column(name = "rua")
    private String rua;
    
    @Column(name = "bairro")
    private String bairro;
    
    @Column(name = "numero")
    private String numero;
    
    @ManyToOne
    @JoinColumn(name = "cidade_id", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_endereco_cidade"))
    private Cidade cidade;
    
    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_pessoa_endereco"))
    private Pessoa pessoa;
    
    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_empresa_endereco"))
    private Empresa empresa;

}
