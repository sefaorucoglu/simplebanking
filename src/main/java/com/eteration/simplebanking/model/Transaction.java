package com.eteration.simplebanking.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DepositTransaction.class, name = "DEPOSIT"),
        @JsonSubTypes.Type(value = WithdrawalTransaction.class, name = "WITHDRAWAL"),
        @JsonSubTypes.Type(value = PayPhoneBillTransaction.class, name = "PHONE")
})
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public LocalDateTime date;

    public double amount;

    private String approvalCode;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @PrePersist
    public void prePersist() {
        this.date = LocalDateTime.now();
        this.approvalCode = UUID.randomUUID().toString();
    }
}
