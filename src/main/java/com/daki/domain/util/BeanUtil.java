/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.util;

/**
 *
 * @author lucas
 */
public class BeanUtil {
 
    public static final <T> Class<T> createClass(String className, ClassLoader classLoader) {
        try {
            return (Class<T>) Class.forName(className, true, classLoader);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static final <T> T createInstance(String className, ClassLoader classLoader){
        Class<T> clazz = createClass(className);
        return createInstance(clazz);
    } 
    
    public static final <T> T createInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Erro ao criar nova instancia para o Objeto " + clazz, e);
        }
    }

    
    public static final <T> Class<T> createClass(String className){
        try{
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e ) {
            throw new RuntimeException(e);
        }
    }
}
