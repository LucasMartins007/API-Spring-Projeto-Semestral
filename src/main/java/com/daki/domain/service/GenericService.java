/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.service;

import com.daki.persistence.pattern.CDIProviderFactory;
import java.util.List;

/**
 *
 * @author lucas
 */
public class GenericService {
    
    protected <T> T getReference(Class<T> clazz) {
        return CDIProviderFactory.getCDIProvider().getReference(clazz);
    }

    protected <T> List<T> getReferences(Class<T> clazz) {
        return CDIProviderFactory.getCDIProvider().getReferences(clazz);
    }
}
