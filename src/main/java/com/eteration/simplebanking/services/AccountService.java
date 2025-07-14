package com.eteration.simplebanking.services;


import com.eteration.simplebanking.dto.TransactionRequestDto;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account findAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).
                orElseThrow(() -> new NoSuchElementException("Account not found for account ID: " + accountNumber));
    }
    @Transactional
    public ResponseEntity<TransactionStatus> credit(String accountNumber, TransactionRequestDto transactionRequestDto) {
        DepositTransaction depositTransaction = new DepositTransaction();
        depositTransaction.setAmount(transactionRequestDto.getAmount());
        Account account = findAccount(accountNumber);
        account.setBalance(account.getBalance() + depositTransaction.getAmount());
        account.post(depositTransaction);
        accountRepository.save(account);
        return new ResponseEntity<> (new TransactionStatus("OK", account.getTransactions().get(0).getApprovalCode()), HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<TransactionStatus> debit(String accountNumber, TransactionRequestDto transactionRequestDto) throws InsufficientBalanceException {
        Account account = findAccount(accountNumber);
        if(account.getBalance() < transactionRequestDto.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction();
        withdrawalTransaction.setAmount(transactionRequestDto.getAmount());
        account.setBalance(account.getBalance() - withdrawalTransaction.getAmount());
        account.post(withdrawalTransaction);
        accountRepository.save(account);
        return new ResponseEntity<> (new TransactionStatus("OK", account.getTransactions().get(0).getApprovalCode()), HttpStatus.OK);
    }
    @Transactional
    /*There should be more business logic for bill payment,
    but for now just working as balance update, like debit process*/
    public ResponseEntity<TransactionStatus> payBill(String accountNumber, TransactionRequestDto transactionRequestDto) throws InsufficientBalanceException {
        Account account = findAccount(accountNumber);
        if(account.getBalance() < transactionRequestDto.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        PayPhoneBillTransaction payPhoneBillTransaction = new PayPhoneBillTransaction();
        payPhoneBillTransaction.setAmount(transactionRequestDto.getAmount());
        account.setBalance(account.getBalance() - payPhoneBillTransaction.getAmount());
        account.post(payPhoneBillTransaction);
        accountRepository.save(account);
        return new ResponseEntity<> (new TransactionStatus("OK", account.getTransactions().get(0).getApprovalCode()), HttpStatus.OK);
    }

}
