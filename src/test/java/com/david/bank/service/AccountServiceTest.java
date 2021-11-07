package com.david.bank.service;

import com.david.bank.controller.dto.AccountResponse;
import com.david.bank.controller.dto.TransactionResponse;
import com.david.bank.exception.AccountNotFoundException;
import com.david.bank.exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Sql({"testData.sql"})
@SpringBootTest
class AccountServiceTest {

    @Autowired
    private IAccountService accountService;


    @Test
    public void getAccountListByUserName_name_not_found(){
        UserNotFoundException thrown = Assertions.assertThrows(UserNotFoundException.class, () -> {
            List<AccountResponse> testResponse = accountService.getAccountListByUserName("hello", LocalDateTime.now());
        });
        Assertions.assertEquals("hello not found", thrown.getMessage());
    }

    @Test
    public void getAccountListByUserName_nameFound(){
        List<AccountResponse> testResponse = accountService.getAccountListByUserName("DavidChow", LocalDateTime.now());
        Assertions.assertEquals(2,testResponse.size());
        Assertions.assertEquals("123-2223-212",testResponse.get(0).getAccountNumber());
        Assertions.assertEquals("123-2223-213",testResponse.get(1).getAccountNumber());
        Assertions.assertEquals("SGSaving726",testResponse.get(0).getAccountName());
        Assertions.assertEquals("AUSaving726",testResponse.get(1).getAccountName());
        Assertions.assertEquals("Savings",testResponse.get(0).getAccountType());
        Assertions.assertEquals("Savings",testResponse.get(1).getAccountType());
        Assertions.assertEquals("SGD",testResponse.get(0).getCurrency());
        Assertions.assertEquals("AUD",testResponse.get(1).getCurrency());
        Assertions.assertEquals(BigDecimal.valueOf(8).stripTrailingZeros(),testResponse.get(0).getBalance().stripTrailingZeros());
        Assertions.assertEquals(BigDecimal.valueOf(8).stripTrailingZeros(),testResponse.get(1).getBalance().stripTrailingZeros());
        Assertions.assertNotNull(testResponse.get(0).getBalanceDate());
        Assertions.assertNotNull(testResponse.get(1).getBalanceDate());
    }

    @Test
    public void getTransactionByAccountNumber_not_found(){
        AccountNotFoundException thrown = Assertions.assertThrows(AccountNotFoundException.class, () -> {
            List<TransactionResponse> testResponse = accountService. getTransactionByAccountNumber("123-2223-214", 1,1000,LocalDateTime.now());
        });
        Assertions.assertEquals("123-2223-214 not found", thrown.getMessage());
    }

    @Test
    public void getTransactionByAccountNumber_found(){
        List<TransactionResponse> testResponse = accountService. getTransactionByAccountNumber("123-2223-213",0,1000, LocalDateTime.of(2021,11,7,0,0));
        Assertions.assertEquals(3,testResponse.size());
        Assertions.assertEquals("123-2223-213",testResponse.get(0).getAccountNumber());
        Assertions.assertEquals("AUSaving726",testResponse.get(0).getAccountName());
        Assertions.assertEquals("AUD",testResponse.get(0).getCurrency());
        Assertions.assertEquals(null,testResponse.get(0).getTransactionNarrative());
        Assertions.assertEquals("DEBIT",testResponse.get(0).getCreditDebit());
        Assertions.assertEquals(null,testResponse.get(0).getCreditAmount());
        Assertions.assertEquals(10.00,testResponse.get(0).getDebitAmount().doubleValue());
        Assertions.assertEquals(LocalDateTime.of(2021,1,1,0,0),testResponse.get(0).getValueDate());

        Assertions.assertEquals("123-2223-213",testResponse.get(1).getAccountNumber());
        Assertions.assertEquals("AUSaving726",testResponse.get(1).getAccountName());
        Assertions.assertEquals("AUD",testResponse.get(1).getCurrency());
        Assertions.assertEquals(null,testResponse.get(1).getTransactionNarrative());
        Assertions.assertEquals("CREDIT",testResponse.get(1).getCreditDebit());
        Assertions.assertEquals(1.00,testResponse.get(1).getCreditAmount().doubleValue());
        Assertions.assertEquals(null,testResponse.get(1).getDebitAmount());
        Assertions.assertEquals(LocalDateTime.of(2021,1,1,1,0),testResponse.get(1).getValueDate());

        Assertions.assertEquals("123-2223-213",testResponse.get(2).getAccountNumber());
        Assertions.assertEquals("AUSaving726",testResponse.get(2).getAccountName());
        Assertions.assertEquals("AUD",testResponse.get(2).getCurrency());
        Assertions.assertEquals(null,testResponse.get(2).getTransactionNarrative());
        Assertions.assertEquals("CREDIT",testResponse.get(2).getCreditDebit());
        Assertions.assertEquals(1.00,testResponse.get(2).getCreditAmount().doubleValue());
        Assertions.assertEquals(null,testResponse.get(2).getDebitAmount());
        Assertions.assertEquals(LocalDateTime.of(2021,11,1,1,0),testResponse.get(2).getValueDate());
    }
    @Test
    public void getTransactionByAccountNumber_found_page1(){
        List<TransactionResponse> testResponse = accountService. getTransactionByAccountNumber("123-2223-213",1,2, LocalDateTime.of(2021,11,7,0,0));
        Assertions.assertEquals(1,testResponse.size());


        Assertions.assertEquals("123-2223-213",testResponse.get(0).getAccountNumber());
        Assertions.assertEquals("AUSaving726",testResponse.get(0).getAccountName());
        Assertions.assertEquals("AUD",testResponse.get(0).getCurrency());
        Assertions.assertEquals(null,testResponse.get(0).getTransactionNarrative());
        Assertions.assertEquals("CREDIT",testResponse.get(0).getCreditDebit());
        Assertions.assertEquals(1.00,testResponse.get(0).getCreditAmount().doubleValue());
        Assertions.assertEquals(null,testResponse.get(0).getDebitAmount());
        Assertions.assertEquals(LocalDateTime.of(2021,11,1,1,0),testResponse.get(0).getValueDate());
    }
    @Test
    public void getTransactionByAccountNumber_found_page0(){
        List<TransactionResponse> testResponse = accountService. getTransactionByAccountNumber("123-2223-213",0,2, LocalDateTime.of(2021,11,7,0,0));
        Assertions.assertEquals(2,testResponse.size());
        Assertions.assertEquals("123-2223-213",testResponse.get(0).getAccountNumber());
        Assertions.assertEquals("AUSaving726",testResponse.get(0).getAccountName());
        Assertions.assertEquals("AUD",testResponse.get(0).getCurrency());
        Assertions.assertEquals(null,testResponse.get(0).getTransactionNarrative());
        Assertions.assertEquals("DEBIT",testResponse.get(0).getCreditDebit());
        Assertions.assertEquals(null,testResponse.get(0).getCreditAmount());
        Assertions.assertEquals(10.00,testResponse.get(0).getDebitAmount().doubleValue());
        Assertions.assertEquals(LocalDateTime.of(2021,1,1,0,0),testResponse.get(0).getValueDate());

        Assertions.assertEquals("123-2223-213",testResponse.get(1).getAccountNumber());
        Assertions.assertEquals("AUSaving726",testResponse.get(1).getAccountName());
        Assertions.assertEquals("AUD",testResponse.get(1).getCurrency());
        Assertions.assertEquals(null,testResponse.get(1).getTransactionNarrative());
        Assertions.assertEquals("CREDIT",testResponse.get(1).getCreditDebit());
        Assertions.assertEquals(1.00,testResponse.get(1).getCreditAmount().doubleValue());
        Assertions.assertEquals(null,testResponse.get(1).getDebitAmount());
        Assertions.assertEquals(LocalDateTime.of(2021,1,1,1,0),testResponse.get(1).getValueDate());

    }



}