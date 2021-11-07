package com.david.bank.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userName) {
        super(userName +" not found");
    }
}
