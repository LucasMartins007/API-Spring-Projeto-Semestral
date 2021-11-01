/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.persistence.util;

import com.daki.domain.util.ListUtil;
import com.daki.persistence.pattern.CDIProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;



/**
 *
 * @author lucas
 */
public class CDIUtil implements CDIProvider {
    
    @Override
    public <T> List<T> getReferences(Class<T> type) {
        BeanManager beanManager = CDI.current().getBeanManager();

        List<T> references = new ArrayList<>();
        Set<Bean<?>> beans = beanManager.getBeans(type);
        for (Bean<?> bean : beans) {
            CreationalContext<?> createCreationalContext = beanManager.createCreationalContext(bean);
            Object reference = beanManager.getReference(bean, type, createCreationalContext);
            references.add((T) reference);
        }
        return references;
    }

    @Override
    public <T> T getReference(Class<T> type) {
        return (T) ListUtil.first(getReferences(type));
    }

    @Override
    public <T> T getRequiredReference(Class<T> type) {
        List<T> references = getReferences(type);

        if (references.isEmpty()) {
            throw new RuntimeException("Não foi encontrado implementação para a interface " + type.getTypeName());
        }

        if (references.size() > 1) {
            throw new RuntimeException("Foi encontrado mais de uma implementação para a interface " + type.getTypeName());
        }

        return ListUtil.first(references);
    }

}
