/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.util;

import java.math.BigDecimal;

/**
 *
 * @author lucas
 */
class BigDecimalUtil {
    
    public static BigDecimal valueOf(String value) {
		if (value != null) {
			Double doubleValue = Double.valueOf(value);
			return BigDecimal.valueOf(doubleValue);
		}
		return null;
	}
}
