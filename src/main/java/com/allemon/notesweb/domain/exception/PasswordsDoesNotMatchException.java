package com.allemon.notesweb.domain.exception;

public class PasswordsDoesNotMatchException extends AuthException {

    public PasswordsDoesNotMatchException(String message) {
        super(message);
    }
}
