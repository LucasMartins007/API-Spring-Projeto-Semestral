/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daki.persistence.repository;

import com.daki.domain.model.PessoaEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface PessoaEmpresaRepository extends JpaRepository<PessoaEmpresa, Integer>{
    
}
