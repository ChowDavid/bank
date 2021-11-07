package com.david.bank.controller;

import com.david.bank.controller.dto.AccountResponse;
import com.david.bank.controller.dto.TransactionResponse;
import com.david.bank.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bank/account")
public class BankAccountController {

    @Autowired
    private IAccountService accountService;

    private LocalDateTime dateTime = LocalDateTime.now();

    @GetMapping("/list")
    public List<AccountResponse> getAccountListByUserName(@RequestParam(value="userName")String userName){
        log.info("/bank/account/list userName {} called",userName);
        return accountService.getAccountListByUserName(userName, dateTime);
    }

    @GetMapping("/transaction")
    public List<TransactionResponse> getAccountTransactionByAccountNumber(@RequestParam(value="accountNumber") String accountNumber){
        log.info("/bank/account/transaction accountNumber {} called",accountNumber);
        return accountService.getTransactionByAccountNumber(accountNumber,dateTime);
    }
}
