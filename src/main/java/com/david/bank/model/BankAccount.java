package com.david.bank.model;

import com.david.bank.constant.AccountType;
import com.david.bank.constant.CurrencyType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
public class BankAccount {
    @Id
    private String accountNumber;
    private String accountName;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    @OneToMany(mappedBy="bankAccount")
    private Set<Transaction> transactions;


}
