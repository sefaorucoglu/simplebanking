package com.eteration.simplebanking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WITHDRAWAL")
@NoArgsConstructor
@Getter
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(double amount) {
        super();
        this.setAmount(amount);
    }
}


