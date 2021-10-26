/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model;

import com.daki.domain.model.enums.EnumTipoPagamento;
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
@Table(name = "pagamento")
@SequenceGenerator(name = "sequence_pagamento", sequenceName = "sequence_pagamento")
public class Pagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_pagamento")
    private Long id;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "parcela")
    private Integer parcela;

    @Column(name = "confirmado")
    private boolean confirmado;

    @Column(name = "tipo_pagamento")
    private EnumTipoPagamento tipoPagamento;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_pagamento_pedido"))
    private Pedido pedido;
}
