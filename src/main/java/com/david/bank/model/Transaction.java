package com.david.bank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private LocalDateTime valueDate;
    @ManyToOne
    @JoinColumn(name="accountNumber", nullable=false)
    private BankAccount bankAccount;
    private String transactionNarrative;
}
