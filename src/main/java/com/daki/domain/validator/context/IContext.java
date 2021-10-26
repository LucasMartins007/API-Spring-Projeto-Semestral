/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.validator.context;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author lucas
 */
@NoRepositoryBean
public interface IContext {
    
    IContext context = new ContextImpl();

    void createBean(Class domainClass);

    void destroyBean(Class domainClass);

    <T> T getBean(Class<T> domainClass);

    <T> Collection<T> getBeansInterface(Class<T> domainClassInterface);

    boolean hasRepositoryFor(Class<?> domainClass);

    <T, ID extends Object> JpaRepository<T, ID> getRepositoryFromClass(Class<T> domainClass);

    static IContext context() {
        return context;
    }

}
