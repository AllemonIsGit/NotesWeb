package com.allemon.notesweb.domain.exceptions;

public class EmailAlreadyExistsException extends AuthException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
