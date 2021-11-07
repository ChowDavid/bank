package com.david.bank.exception;


public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String accountNumber) {
        super(accountNumber +" not found");
    }
}
