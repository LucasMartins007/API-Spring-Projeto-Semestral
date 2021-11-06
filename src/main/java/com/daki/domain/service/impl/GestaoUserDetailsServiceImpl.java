/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.service.impl;

import com.daki.domain.exception.DomainException;
import com.daki.domain.exception.enums.EnumDomainException;
import com.daki.domain.model.Pessoa;
import com.daki.domain.model.classes.CustomUserDetails;
import com.daki.domain.model.dto.RoleDto;
import com.daki.domain.model.dto.UsuarioDto;
import com.daki.domain.service.AbstractService;
import com.daki.domain.service.UserDetailsService;
import com.daki.domain.util.ListUtil;
import com.daki.domain.util.StringUtil;
import com.daki.persistence.repository.PessoaRepository;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class GestaoUserDetailsServiceImpl extends AbstractService<Pessoa, Integer> implements UserDetailsService {
    
    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (StringUtil.isNullOrEmpty(email)) {
            throw new UsernameNotFoundException(EnumDomainException.USERNAME_REQUIRED.getMessage());
        }
        
        final Pessoa pessoa = getRepository(PessoaRepository.class).findByEmail(email);
        if(pessoa == null){
            throw new DomainException(EnumDomainException.LOGIN_NOT_FOUND.getMessage());
        }
        
        RoleDto role = new RoleDto();
        role.setId(3);
        role.setNome("ROLE_USER");
        
        final UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setName(pessoa.getNome());
        usuarioDto.setPassword(pessoa.getSenha());
        usuarioDto.setRole(role);
        
        final CustomUserDetails user = new CustomUserDetails(pessoa.getNome(), pessoa.getSenha(), getGrantedAuthorities());
        user.setUsuario(usuarioDto);
        
        return user;
    }

    private List<GrantedAuthority> getGrantedAuthorities() {
        return ListUtil.toList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
