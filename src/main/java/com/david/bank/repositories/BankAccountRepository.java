package com.david.bank.repositories;

import com.david.bank.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount,String> {
    BankAccount findByAccountNumber(String accoundNumber);
}
