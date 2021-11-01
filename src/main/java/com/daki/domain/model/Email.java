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
@Table(name = "pessoa_email")
@SequenceGenerator(name = "seq_pessoa_email", sequenceName = "seq_pessoa_email")
public class Email implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_pessoa_email")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "principal")
    private boolean principal;

    @Column(name = "ativo")
    private boolean ativo;
    
    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_pessoa_email"))
    private Pessoa pessoa;
    
    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_empresa_email"))
    private Empresa empresa;

}
