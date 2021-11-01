/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.persistence.repository;

import com.daki.domain.model.Email;
import com.daki.domain.model.Pessoa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
    
    @Query("select p from Pessoa p "
            + "inner join p.emails e "
            + "where lower(e.email) = :email")
    List<Pessoa> findAllByEmails(String email);
    
    @Query("select p from Pessoa p where p.cpf = :cpf ")
    List<Pessoa> findAllByCpf(String cpf);
    
}
