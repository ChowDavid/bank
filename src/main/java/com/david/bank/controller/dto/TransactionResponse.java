package com.david.bank.controller.dto;

import com.david.bank.model.Transaction;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class TransactionResponse {
    private String accountNumber;
    private String accountName;
    private LocalDateTime valueDate;
    private String currency;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    private String creditDebit;
    private String transactionNarrative;

    public TransactionResponse(Transaction t){
        this.accountNumber = t.getBankAccount().getAccountNumber();
        this.accountName = t.getBankAccount().getAccountName();
        this.valueDate = t.getValueDate();
        this.currency = t.getBankAccount().getCurrency().name();
        if (t.getAmount().compareTo(BigDecimal.ZERO)>0){
            this.creditDebit="DEBIT";
            this.debitAmount=t.getAmount();
        } else {
            this.creditDebit="CREDIT";
            this.creditAmount=t.getAmount().abs();
        }
        this.transactionNarrative = t.getTransactionNarrative();
    }
}
