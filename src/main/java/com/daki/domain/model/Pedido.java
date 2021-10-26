/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model;

import com.daki.domain.model.enums.EnumFormaEntrega;
import com.daki.domain.model.enums.EnumStatusPedido;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "pedido")
@SequenceGenerator(name = "sequence_pedido", sequenceName = "sequence_pedido")
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_pedido")
    private Long id;
    
    private Date dataInclusao;
    
    private Date dataAlteracao;
    
    private EnumStatusPedido statusPedido;
    
    private EnumFormaEntrega formaEntrega;
    
    private Pagamento pagamento;
    
    private Pessoa cliente;
    
    private Empresa empresa;
}
