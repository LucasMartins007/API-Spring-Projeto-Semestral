/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model.dto;

import com.daki.api.converter.TransientFieldDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author lucas
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UsuarioDto {
    
    private Integer id;

    private String name;

    private String username;

    @TransientFieldDto
    private String password;

    private RoleDto role;

    private Integer professionalId;
}
