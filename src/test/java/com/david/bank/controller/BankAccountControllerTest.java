package com.david.bank.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void accountList_found() throws Exception {
        mockMvc.perform(get("/bank/account/list?userName=DavidChow"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"accountNumber\":\"123-2223-212\",\"accountName\":\"SGSaving726\",\"accountType\":\"Savings\"")));

    }
    @Test
    public void accountList_notFound() throws Exception {
        mockMvc.perform(get("/bank/account/list?userName=DavidCho"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("{\"error\":\"USER_NOT_FOUND")));

    }

    @Test
    public void transaction_notFound() throws Exception {
        mockMvc.perform(get("/bank/account/transaction?accountNumber=123456"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("ACCOUNT_NOT_FOUND")));
    }
    @Test
    public void transaction_Found() throws Exception {
        mockMvc.perform(get("/bank/account/transaction?accountNumber=123-2223-212"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"accountNumber\":\"123-2223-212\",\"accountName\":\"SGSaving726\",\"valueDate\":\"2021-01-01T00:00:00\",\"currency\":\"SGD\",\"debitAmount\":10.00,\"creditAmount\":null,\"creditDebit\":\"DEBIT\",\"transactionNarrative\":null},{\"accountNumber\":\"123-2223-212\",\"accountName\":\"SGSaving726\",\"valueDate\":\"2021-01-01T01:00:00\",\"currency\":\"SGD\",\"debitAmount\":null,\"creditAmount\":1.00,\"creditDebit\":\"CREDIT\",\"transactionNarrative\":null},{\"accountNumber\":\"123-2223-212\",\"accountName\":\"SGSaving726\",\"valueDate\":\"2021-11-01T01:00:00\",\"currency\":\"SGD\",\"debitAmount\":null,\"creditAmount\":1.00,\"creditDebit\":\"CREDIT\",\"transactionNarrative\":null}]")));
    }

}