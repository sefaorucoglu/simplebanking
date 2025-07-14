package com.eteration.simplebanking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PHONE")
@NoArgsConstructor
@Getter
public class PayPhoneBillTransaction extends Transaction {
    private String operator;

    private String phoneNumber;

    public PayPhoneBillTransaction(String operator, String phoneNumber, double amount) {
        super();
        this.setAmount(amount);
        this.operator = operator;
        this.phoneNumber = phoneNumber;
    }
}
