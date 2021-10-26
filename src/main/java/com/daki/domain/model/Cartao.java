/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model;

import com.daki.domain.model.enums.EnumTipoCartao;
import com.daki.domain.model.enums.EnumBandeiraCartao;
import java.io.Serializable;
import java.util.Date;
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
@Table(name = "Cartao")
@SequenceGenerator(name = "seq_cartao", sequenceName = "seq_cartao")
public class Cartao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_cartao")
    private Long Id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "numero")
    private String numero;

    @Column(name = "validade")
    private Date validade;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "bandeira")
    private EnumBandeiraCartao bandeira;

    @Column(name = "tipo_cartao")
    private EnumTipoCartao tipoCartao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_cartao_pessoa"))
    private Pessoa pessoa;

}
