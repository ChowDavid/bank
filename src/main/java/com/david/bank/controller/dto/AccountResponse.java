package com.david.bank.controller.dto;

import com.david.bank.model.BankAccount;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class AccountResponse {
    private String accountNumber;
    private String accountName;
    private String accountType;
    private LocalDate balanceDate;
    private String currency;
    private BigDecimal balance;

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountResponse(BankAccount account){
        this.accountNumber = account.getAccountNumber();
        this.accountName = account.getAccountName();
        this.accountType = account.getAccountType().getDescription();
        this.balanceDate = LocalDate.now();
        this.currency = account.getCurrency().name();
        this.balance = BigDecimal.ZERO;
    }
}
