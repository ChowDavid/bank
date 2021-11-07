package com.david.bank.repositories;

import com.david.bank.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository  extends CrudRepository<Transaction,Long> {
    List<Transaction> findByBankAccount_AccountNumberAndValueDateBefore(String accountNumber, LocalDateTime valueDateBefore);
}
