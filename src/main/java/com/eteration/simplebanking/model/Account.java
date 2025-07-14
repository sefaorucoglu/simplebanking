package com.eteration.simplebanking.model;


import com.eteration.simplebanking.exception.InsufficientBalanceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="account_seq")
    @SequenceGenerator( name = "account_seq" , sequenceName = "account_seq" , allocationSize = 1)
    private Long id;
    public String owner;
    public String accountNumber;
    public double balance;
    @OneToMany
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void post(Transaction transaction) {
        this.transactions.add(transaction);
    }

}
