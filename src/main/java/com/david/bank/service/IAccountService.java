package com.david.bank.service;

import com.david.bank.controller.dto.AccountResponse;
import com.david.bank.controller.dto.TransactionResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface IAccountService {
    List<AccountResponse> getAccountListByUserName(String userName, LocalDateTime dateTime);
    List<TransactionResponse> getTransactionByAccountNumber(String accountNumber, LocalDateTime dateTime);

}
