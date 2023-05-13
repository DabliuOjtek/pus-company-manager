package com.pus.companymanager.controller;


import com.pus.companymanager.dto.exception.DefaultException;
import com.pus.companymanager.dto.exception.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DefaultException.class)
    protected ResponseEntity<ExceptionBody> handleDefaultException(DefaultException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionBody body = new ExceptionBody(status.value(), status.getReasonPhrase(), ex.getErrors());
        return new ResponseEntity<>(body, HttpStatus.valueOf(body.getStatus()));
    }
}
