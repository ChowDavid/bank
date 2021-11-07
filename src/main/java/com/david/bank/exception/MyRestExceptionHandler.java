package com.david.bank.exception;

import com.david.bank.controller.dto.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.david.bank.constant.ErrorType.ACCOUNT_NOT_FOUND;
import static com.david.bank.constant.ErrorType.USER_NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class MyRestExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(UserNotFoundException.class)
    public  ResponseEntity<ApiError> handleUserNotFoundException(
            UserNotFoundException ex) {
        ApiError apiError = new ApiError(USER_NOT_FOUND,ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AccountNotFoundException.class)
    public  ResponseEntity<ApiError> handleAccountNotFoundException(
            AccountNotFoundException ex) {
        ApiError apiError = new ApiError(ACCOUNT_NOT_FOUND,ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
