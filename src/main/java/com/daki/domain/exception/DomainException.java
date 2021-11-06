package com.daki.domain.exception;

import java.text.MessageFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author lucas
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DomainException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DomainException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }

    @FunctionalInterface
    public interface Check<T> {
        T checked();
    }

    public static <T> T checked(Check<T> next) {
        try {
            return next.checked();
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }
}
