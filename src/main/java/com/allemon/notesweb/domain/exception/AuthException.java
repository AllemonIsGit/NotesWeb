package com.allemon.notesweb.domain.exception;

public abstract class AuthException extends RuntimeException {
    
    public AuthException(String message) {
        super(message);
    }
}
