package com.eteration.simplebanking.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WITHDRAWAL")
@NoArgsConstructor
public class payPhoneBillTransaction extends Transaction{
    @Override
    public void execute() {
        getAccount().setBalance(getAccount().getBalance() - getAmount());
    }
}
