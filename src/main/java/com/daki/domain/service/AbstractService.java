/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daki.domain.service;


import com.daki.domain.exception.DomainException;
import com.daki.domain.validator.context.IContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;


/**
 *
 * @author lucas
 * @param <I> Identificador da chave primária do repositorio Integer, Long
 * @param <E> Classe de entidade
 *
 */
public class AbstractService<E, I> {
    
    protected Class<E> entityClazz;
    
    private IContext getContext() {
        return IContext.context();
    }
    
    public JpaRepository<E, I> getRepository() {
        return getContext().getRepositoryFromClass(entityClazz);
    }
    
    public <R extends Repository> R getRepository(Class<R> classRespository) {
        return getContext().getBean(classRespository);
    }

    public <R> R getService(Class<R> classService) {
        return getContext().getBean(classService);
    }
    
    public E findAndValidate(I id) {
        if (id == null) {
            throw new DomainException("Mensagem de nullpointer");
        }
        return getRepository()
                .findById(id)
                .orElseThrow(() -> new DomainException("Entidade não encontrada", entityClazz.getSimpleName(), id));
    }

    
}
