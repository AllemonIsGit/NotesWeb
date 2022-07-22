package com.allemon.notesweb.domain.exceptions;

public class PasswordsDoesNotMatchException extends AuthException {

    public PasswordsDoesNotMatchException(String message) {
        super(message);
    }
}
