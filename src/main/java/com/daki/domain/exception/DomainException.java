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

}
