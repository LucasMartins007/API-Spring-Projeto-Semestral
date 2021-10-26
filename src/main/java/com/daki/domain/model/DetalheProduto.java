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
@Table(name = "detalhe_produto")
@SequenceGenerator(name = "sequence_detalhe_produto", sequenceName = "sequence_detalhe_produto")
public class DetalheProduto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_detalhe_produto")
    private Long id;

    @Column(name = "cor")
    private String cor;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "tamanho")
    private String tamanho;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_detalhe_produto"))
    private Produto produto;
}
