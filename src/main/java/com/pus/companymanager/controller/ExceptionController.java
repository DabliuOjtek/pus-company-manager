package com.pus.companymanager.controller;


import com.pus.companymanager.exception.DefaultException;
import com.pus.companymanager.exception.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DefaultException.class)
    protected ResponseEntity<ExceptionBody> handleDefaultException(DefaultException ex) {
        ExceptionBody body = new ExceptionBody(ex.getStatus().value(), ex.getStatus().getReasonPhrase(), ex.getErrors());
        return new ResponseEntity<>(body, HttpStatus.valueOf(body.getStatus()));
    }

}
