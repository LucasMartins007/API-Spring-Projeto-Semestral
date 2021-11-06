/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author lucas
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleDto {

    private Integer id;

    private String nome;

}
