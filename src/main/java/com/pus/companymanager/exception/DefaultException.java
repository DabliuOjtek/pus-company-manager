package com.pus.companymanager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DefaultException extends RuntimeException{
    private List<String> errors = new ArrayList<>();
    private final HttpStatus status;

    public DefaultException(String message, HttpStatus status) {
        this.errors.add(message);
        this.status = status;
    }

    public DefaultException(List<String> errors, HttpStatus status) {
        this.errors = errors;
        this.status = status;
    }
}
