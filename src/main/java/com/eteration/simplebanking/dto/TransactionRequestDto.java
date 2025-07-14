package com.eteration.simplebanking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionRequestDto {
    private double amount;
}
