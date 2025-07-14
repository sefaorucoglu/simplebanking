package com.eteration.simplebanking.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DEPOSIT")
@NoArgsConstructor
@Getter
public class DepositTransaction extends Transaction {
    public DepositTransaction(double amount) {
        super();
        this.setAmount(amount);
    }
}
