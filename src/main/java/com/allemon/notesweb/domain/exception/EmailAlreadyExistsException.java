package com.allemon.notesweb.domain.exception;

public class EmailAlreadyExistsException extends AuthException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
