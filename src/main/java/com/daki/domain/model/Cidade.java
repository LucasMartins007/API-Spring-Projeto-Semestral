package com.daki.domain.model;

import java.util.List;
import javax.persistence.CascadeType;
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
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucas
 */
@Getter
@Setter
@Entity
@Table(name = "cidade")
@SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_cidade")
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_cidade_estado"))
    private Estado estado;

    @OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;

}
