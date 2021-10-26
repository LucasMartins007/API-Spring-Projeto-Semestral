/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucas
 */
@Getter
@Setter
@Entity
@Table(name = "produto")
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto")
public class Produto implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_produto")
    private Long id;
    
    @Column(name = "descricao_comercial")
    private String descricaoComercial;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "quantidade")
    private int quantidade;
    
    @Column(name = "preco_compra")
    private Double precoCompra;
    
    @Column(name = "preco_venda")
    private Double precoVenda;
    
    @Column(name = "ativo")
    private boolean ativo;
    
    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    
    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    
    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_produto_empresa"))
    private Empresa empresa;
    
    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalheProduto> detalheProduto;
}
