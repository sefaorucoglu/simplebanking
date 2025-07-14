package com.eteration.simplebanking.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WITHDRAWAL")
@NoArgsConstructor
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(double amount) {
        super();
        this.setAmount(amount);
    }
}


