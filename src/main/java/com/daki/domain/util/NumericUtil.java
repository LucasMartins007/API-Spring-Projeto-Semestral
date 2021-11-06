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
public class NumericUtil {

    public synchronized static boolean isEquals(BigDecimal one, BigDecimal other) {
        if (one != null && other == null) {
            return false;
        } else if (one == null && other != null) {
            return false;
        } else {
            return one.compareTo(other) == 0;
        }

    }

    public static boolean isNumeric(String str) {
        return str.matches("^-?[0-9]+(\\.[0-9]+)?$");
    }

    public synchronized static boolean isEquals(Number number, Number compareTo) {
        return isEquals(new BigDecimal(number.doubleValue()), new BigDecimal(compareTo.doubleValue()));
    }

    public synchronized static boolean isGreater(Number number, Number compareTo) {
        return number != null && compareTo != null && number.doubleValue() > compareTo.doubleValue();
    }

    public synchronized static boolean isGreaterOrEquals(Number number, Number compareTo) {
        return number != null && compareTo != null && number.doubleValue() >= compareTo.doubleValue();
    }

    public synchronized static boolean isGreaterOrEqualsZero(Number number) {
        return isGreaterOrEquals(number, 0);
    }

    public synchronized static boolean isGreaterThanZero(Number number) {
        return isGreater(number, 0);
    }

    public synchronized static boolean isLess(Number number, Number compareTo) {
        return number != null && number.doubleValue() < compareTo.doubleValue();
    }

    public synchronized static boolean isLessOrEquals(Number number, Number compareTo) {
        return number != null && number.doubleValue() <= compareTo.doubleValue();
    }

    public synchronized static boolean isLessOrEqualsZero(Number number) {
        return isLessOrEquals(number, 0);
    }

    public synchronized static boolean isLessThanZero(Number number) {
        return isLess(number, 0);
    }

    public synchronized static double getPercentage(double value, double percent) {
        value = value * (percent / 100);

        return value;
    }

    public synchronized static BigDecimal getPercentage(BigDecimal value, BigDecimal percent) {
        double percentage = getPercentage(value.doubleValue(), percent.doubleValue());
        return new BigDecimal(percentage);
    }

    public synchronized static BigDecimal getNotNullOrZero(BigDecimal value) {
        return value != null ? value : BigDecimal.ZERO;
    }

    public synchronized static Integer getNotNullOrZero(Integer value) {
        return value != null ? value : 0;
    }

    public synchronized static Byte getNotNullOrZero(Byte value) {
        return value != null ? value : new Byte("0");
    }

    public synchronized static Short getNotNullOrZero(Short value) {
        return value != null ? value : new Short("0");
    }

    public synchronized static Float getNotNullOrZero(Float value) {
        return value != null ? value : new Float(0);
    }

    public synchronized static Double getNotNullOrZero(Double value) {
        return value != null ? value : new Double(0);
    }

    public synchronized static String onlyDigits(String str) {
        if (str == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        char[] charArray = str.toCharArray();

        for (char c : charArray) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static BigDecimal min(BigDecimal a, BigDecimal b) {
        return new BigDecimal(Math.min(a.doubleValue(), b.doubleValue()));
    }

    public static BigDecimal max(BigDecimal a, BigDecimal b) {
        return new BigDecimal(Math.max(a.doubleValue(), b.doubleValue()));
    }

    public synchronized static boolean isIntegerValue(Double value) {
        return value != null && value % 1 == 0;
    }
    
    public static Integer parseInt(Object objVal) {
        if (objVal != null) {
            return parseInt(objVal.toString());
        }
        return null;
    }

}
