package com.diff.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception returned when a given diff entry could not be
 * found on the database
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class DiffEntryNotFoundException extends RuntimeException {

    public DiffEntryNotFoundException(String message) {
        super(message);
    }
}
