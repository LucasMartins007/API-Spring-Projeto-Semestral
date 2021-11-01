/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daki.domain.model;

import java.io.Serializable;

/**
 *
 * @author lucas
 */
public interface IIdentifier<T extends Number> extends Serializable {

    T getId();

}

