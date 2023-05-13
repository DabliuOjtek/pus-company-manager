package com.pus.companymanager.dto.exception;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class ExceptionBody {

    private final Integer status;
    private final String error;
    private final List<String> errors;
    private final Integer errorsCount;

    public ExceptionBody(Integer status, String error) {
        this.status = status;
        this.error = error;
        this.errors = null;
        this.errorsCount = 1;
    }

    public ExceptionBody(Integer status, String error, List<String> errors) {
        this.status = status;
        this.error = error;
        this.errors = errors;
        this.errorsCount = errors.size();
    }

    public ExceptionBody(Integer status, String error, String errorMessage) {
        this.status = status;
        this.error = error;
        this.errors = Collections.singletonList(errorMessage);
        this.errorsCount = 1;
    }
}
