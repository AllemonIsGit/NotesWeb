package com.allemon.notesweb.domain.exception;

public class AccessForbiddenException extends RuntimeException{

    public AccessForbiddenException(String message) {
        super(message);
    }
}
