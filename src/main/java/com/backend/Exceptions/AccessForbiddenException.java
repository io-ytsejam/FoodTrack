package com.backend.Exceptions;

public class AccessForbiddenException extends RuntimeException{
    AccessForbiddenException(){
        super("Forbidden access");
    }
}
