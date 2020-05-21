package com.backend.ExceptionHandlers;

import com.backend.Exceptions.AccessForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccessForbiddenAdvice {
    @ResponseBody
    @ExceptionHandler(AccessForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String accessForbiddenHandler(AccessForbiddenException ex){
        return ex.getMessage()+"git(hub) sucks";
    }
}
