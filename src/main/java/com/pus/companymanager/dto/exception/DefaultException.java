package com.pus.companymanager.dto.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DefaultException extends RuntimeException{
    private List<String> errors = new ArrayList<>();

    public DefaultException(String message) {
        this.errors.add(message);
    }

    public DefaultException(List<String> errors) {
        this.errors = errors;
    }
}
