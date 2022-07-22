package com.allemon.notesweb.domain.exceptions;

public abstract class AuthException extends RuntimeException {
    
    public AuthException(String message) {
        super(message);
    }
}
