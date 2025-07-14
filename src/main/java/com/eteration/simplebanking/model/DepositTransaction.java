package com.eteration.simplebanking.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DEPOSIT")
@NoArgsConstructor
public class DepositTransaction extends Transaction {
    public DepositTransaction(double amount) {
        super();
        this.setAmount(amount);
    }
}
