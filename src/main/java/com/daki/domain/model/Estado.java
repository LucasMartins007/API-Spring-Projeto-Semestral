/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "estado")
@SequenceGenerator(name = "seq_estado", sequenceName = "seq_estado")
public class Estado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_pedido")
    private Long id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "uf")
    private String uf;
    
    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cidade> cidade;
    
}
