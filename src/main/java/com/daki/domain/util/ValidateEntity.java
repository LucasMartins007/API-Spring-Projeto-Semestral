/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daki.domain.util;

import com.daki.domain.exception.DomainException;
import com.daki.domain.exception.enums.EnumDomainException;
import com.daki.domain.patterns.EnumDateFormat;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author lucas
 */
public class ValidateEntity {
    
    
    public static void validateGreaterThanZero(Number number, String name) {
        if (number != null && number.doubleValue() <= 0) {
            throw new DomainException(EnumDomainException.CAMPO_MENOR_IGUAL_ZERO.getMessage(), name);
        }
    }

    public static <N extends Number> void validateLessThan(BigDecimal number, BigDecimal min, String name) {
        if (number != null && number.compareTo(min) >= 0) {
            throw new DomainException(EnumDomainException.CAMPO_MENOR_QUE.getMessage(), name, min);
        }
    }

    public static <N extends Number> void validateLessThan(N number, N min, String name) {
        if (number != null && number.doubleValue() >= min.doubleValue()) {
            throw new DomainException(EnumDomainException.CAMPO_MENOR_QUE.getMessage(), name, min);
        }
    }

    public static <N extends Number> void validateLessThanOrEqual(N number, N min, String name) {
        if (number != null && number.doubleValue() > min.doubleValue()) {
            throw new DomainException(EnumDomainException.CAMPO_MENOR_OU_IGUAL_QUE.getMessage(), name, min);
        }
    }

    public static <N extends Number> void validateGreaterThan(N number, N min, String name) {
        if (number != null && number.doubleValue() <= min.doubleValue()) {
            throw new DomainException(EnumDomainException.CAMPO_MAIOR_QUE.getMessage(), name, min);
        }
    }

    public static <N extends Number> void validateGreaterThanOrEqual(N number, N min, String name) {
        if (number != null && number.doubleValue() < min.doubleValue()) {
            throw new DomainException(EnumDomainException.CAMPO_MAIOR_OU_IGUAL_QUE.getMessage(), name, min);
        }
    }

    public static <N extends Number> void validateBetween(N value, N min, N max, String name) {
        if (value != null) {
            final Double minimum = NumericUtil.getNotNullOrZero(min.doubleValue());
            final Double maximun = NumericUtil.getNotNullOrZero(max.doubleValue());

            if (value.doubleValue() < minimum || value.doubleValue() > maximun) {
                throw new DomainException(EnumDomainException.CAMPO_ENTRE.getMessage(), name, min, max);
            }
        }
    }

    public static <N extends Number> void validateMinCaracter(String value, int min, String name) {
        if (value == null || value.trim().length() < min) {
            throw new DomainException(EnumDomainException.CAMPO_MINIMO_CARACTERS.getMessage(), name, min);
        }
    }

    public static <N extends Number> void validateMaxCaracter(String value, int max, String name) {
        if (value != null && value.trim().length() > max) {
            throw new DomainException(EnumDomainException.CAMPO_MAXIMO_CARACTERS.getMessage(), name, max);
        }
    }

    public static void validateMinMaxCaracter(String value, int min, int max, String name) {
        validateMinCaracter(value, min, name);
        validateMaxCaracter(value, max, name);
    }

    public static void validateMinMaxCaracterIfFieldNotNull(String value, int min, int max, String name) {
        if (value != null && !value.isEmpty()) {
            validateMinCaracter(value, min, name);
            validateMaxCaracter(value, max, name);
        }
    }

    public static void validateDateGreaterThen(Date dataInicial, Date dataFinal) {
        validateDateGreaterThen(dataInicial, dataFinal, EnumDateFormat.DDMMYYYYHHMMSS);
    }

    public static void validateHourGreaterThen(Date dataInicial, Date dataFinal) {
        final EnumDateFormat format = EnumDateFormat.HHMM;
        if (DateUtil.compareTo(dataInicial, dataFinal, format) > 0) {
            throw new DomainException(EnumDomainException.HORA_INICIAL_MAIOR_DATA_FINAL.getMessage(), format.format(dataInicial), format.format(dataFinal));
        }
    }

    public static void validateDateGreaterThen(Date dataInicial, Date dataFinal, EnumDateFormat format) {
        if (DateUtil.compareTo(dataInicial, dataFinal, format) > 0) {
            throw new DomainException(EnumDomainException.DATA_INICIAL_MAIOR_DATA_FINAL.getMessage(), format.format(dataInicial), format.format(dataFinal));
        }
    }

    public static void validateDateGreaterThenCurrentDate(Date dataTest, String name) {
        final Date hoje = DateUtil.getDate();
        if (DateUtil.compareTo(dataTest, hoje, EnumDateFormat.DDMMYYYY) > 0) {
            throw new DomainException(EnumDomainException.DATA_MAIOR_DATA_ATUAL.getMessage(), name, EnumDateFormat.DDMMYYYY.format(dataTest));
        }
    }

    public static void validateDateLessThenCurrentDate(Date dataTest, String name) {
        final Date hoje = DateUtil.getDate();
        if (DateUtil.compareTo(hoje, dataTest, EnumDateFormat.DDMMYYYY) > 0) {
            throw new DomainException(EnumDomainException.DATA_MENOR_DATA_ATUAL.getMessage() , name, EnumDateFormat.DDMMYYYY.format(dataTest));
        }
    }

    public static <N extends Number> void validateSizeEqualsCaracter(String value, int size, String name) {
        value = value == null ? "" : value;
        if (value.trim().length() != size) {
            throw new DomainException(EnumDomainException.CAMPO_IGUAL_CARACTERS.getMessage(), name, size);
        }
    }

    public static <N extends Number> void validateSizeEqualsNumber(N value, int size, String name) {
        if (value != null && size != String.valueOf(value).length()) {
            throw new DomainException(EnumDomainException.CAMPO_IGUAL_CARACTERS.getMessage(), name, size);
        }
    }

    public static void validateOnlyNumbers(String value, String name) {
        if (value != null) {
            value = value.replaceAll("[0-9]", "");
            if (value.length() > 0) {
                throw new DomainException(EnumDomainException.CAMPO_SOMENTE_NUMEROS.getMessage(), name);
            }
        }
    }

    
}
