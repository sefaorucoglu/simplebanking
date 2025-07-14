package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.TransactionStatus;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Optional<Account> findAccount(String accountNumber) {
        return accountRepository.findById(Long.valueOf(accountNumber));
    }

    public  Optional<TransactionStatus> credit(String accountNumber, double amount) {
       return null;
    }

    public  Optional<TransactionStatus> debit(String accountNumber, double amount) {
       return null;
    }
    public  Optional<TransactionStatus> payBill(String accountNumber, double amount) {
       return null;
    }

}
