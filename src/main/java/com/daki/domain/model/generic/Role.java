/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model.generic;

import javax.persistence.Column;
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
@Table(name = "role")
public class Role {
    
    public static final String ROOT = "ROLE_ROOT";

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String RECEPTIONIST = "ROLE_RECEPTIONIST";

    public static final String PERSONAL = "ROLE_PERSONAL";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "id_role", allocationSize = 1, sequenceName = "gen_id_role", schema = "public")
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

}
