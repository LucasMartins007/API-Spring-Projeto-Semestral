/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.model.classes;

import com.daki.domain.model.dto.UsuarioDto;
import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author lucas
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomUserDetails extends User {

    private UsuarioDto usuario;

    private String signingKey;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
