package com.david.bank.service;

import com.david.bank.controller.dto.AccountResponse;
import com.david.bank.controller.dto.TransactionResponse;
import com.david.bank.exception.AccountNotFoundException;
import com.david.bank.exception.UserNotFoundException;
import com.david.bank.model.BankAccount;
import com.david.bank.model.Person;
import com.david.bank.model.Transaction;
import com.david.bank.repositories.BankAccountRepository;
import com.david.bank.repositories.PersonRepository;
import com.david.bank.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccountService implements IAccountService{

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;


    /**
     * Return AccountResponse list from userName
     * if cannot find user from Person table. it will throw exception.
     * if accounts found. then create Account Response list
     * The account balance would be calculated from transaction table.
     * @param userName
     * @return
     */
    @Override
    public List<AccountResponse> getAccountListByUserName(String userName,LocalDateTime dateTime) {
        Person person = personRepository.findByUserName(userName);
        if (person==null) {
            log.info("Account not found by userName {}",userName);
            throw new UserNotFoundException(userName);
        }
        List<BankAccount> accounts =personRepository.getAllAccountByUserName(userName);
        log.info("Account found {}",accounts.size());
        return createAccountResponse(accounts,dateTime);
    }

    @Override
    public List<TransactionResponse> getTransactionByAccountNumber(String accountNumber,LocalDateTime dateTime) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber);
        if (bankAccount==null){
            log.info("Transaction not found by accountNumber {}",accountNumber);
            throw new AccountNotFoundException(accountNumber);
        }
        List<Transaction> allTransactionSoFar = transactionRepository.findByBankAccount_AccountNumberAndValueDateBefore(accountNumber,dateTime);
        log.info("Transaction found {}",allTransactionSoFar.size());
        return allTransactionSoFar.stream()
                .sorted(Comparator.comparing(Transaction::getValueDate))
                .map(t->new TransactionResponse(t))
                .collect(Collectors.toList());
    }

    protected List<AccountResponse> createAccountResponse(List<BankAccount> accounts, LocalDateTime dateTime) {
        List<AccountResponse> all =  accounts.stream().map(a->new AccountResponse(a)).collect(Collectors.toList());
        all.forEach(a->a.setBalance(calculateBalance(dateTime,a.getAccountNumber())));
        return all;
    }

    /**
     * Calcualte the account balance lookup from DB for all existing records up to date
     * @param dateTime
     * @param accountNumber
     * @return
     */
    protected BigDecimal calculateBalance(LocalDateTime dateTime, String accountNumber){
        log.info("Calculate the account balance for account Number {} for datetime to up {}",accountNumber,dateTime);
        List<Transaction> allTransactionSoFar = transactionRepository.findByBankAccount_AccountNumberAndValueDateBefore(accountNumber,dateTime);
        log.info("Transaction found {}",allTransactionSoFar.size());
        return allTransactionSoFar.stream().map(t->t.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
