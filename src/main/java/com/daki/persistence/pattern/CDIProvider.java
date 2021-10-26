/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.persistence.pattern;

import java.util.List;

/**
 *
 * @author lucas
 */
public interface CDIProvider {
    
    <T> T getReference(Class<T> type);

    <T> T getRequiredReference(Class<T> type);

    <T> List<T> getReferences(Class<T> type);

}
