package com.david.bank.controller.dto;

import com.david.bank.constant.ErrorType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiError {
    private ErrorType error;
    private LocalDateTime time;
    private String message;

    public ApiError(ErrorType type, String message) {
        this.error = type;
        this.message = message;
        this.time = LocalDateTime.now();
    }
}
