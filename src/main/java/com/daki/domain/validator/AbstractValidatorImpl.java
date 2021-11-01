/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.validator;

import com.daki.domain.validator.interfaces.IValidator;
import com.daki.domain.context.IContext;
import com.daki.domain.model.AbstractEntity;
import java.lang.reflect.ParameterizedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

/**
 * @author lucas
 * @param <T> Classe a ser validada
 */
public abstract class AbstractValidatorImpl<T extends AbstractEntity> implements IValidator<T> {

    private Class<T> entityClass;

    public AbstractValidatorImpl() {
        resolverClass(entityClass);
    }

    private IContext getContext() {
        return IContext.context();
    }

    public JpaRepository<T, Number> getRepository() {
        return getContext().getRepositoryFromClass(entityClass);
    }

    public <R extends Repository> R getRepository(Class<R> classRespository) {
        return getContext().getBean(classRespository);
    }

    public <R> R getService(Class<R> classService) {
        return getContext().getBean(classService);
    }
    
    private Class<T> resolverClass(Class<T> clazzType) {
        if (entityClass == null) {
            if (clazzType == null) {
                entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            } else {
                entityClass = clazzType;
            }
        }
        return entityClass;
    }


}
