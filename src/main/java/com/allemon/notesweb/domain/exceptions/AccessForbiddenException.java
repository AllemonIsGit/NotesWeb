package com.allemon.notesweb.domain.exceptions;

public class AccessForbiddenException extends RuntimeException{

    public AccessForbiddenException(String message) {
        super(message);
    }
}
