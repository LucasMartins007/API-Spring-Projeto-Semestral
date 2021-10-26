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
@Table(name = "pessoa_telefone")
@SequenceGenerator(name = "seq_pessoa_telefone", sequenceName = "seq_pessoa_telefone")
public class Telefone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_pessoa_telefone")
    private Long id;
    
    @Column(name = "telefone")
    private String telefone;
    
    @Column(name = "principal")
    private boolean principal;
    
    @Column(name = "ativo")
    private boolean ativo;
    
    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_pessoa_telefone"))
    private Pessoa pessoa;
    
    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_empresa_telefone"))
    private Empresa empresa;
    
}
