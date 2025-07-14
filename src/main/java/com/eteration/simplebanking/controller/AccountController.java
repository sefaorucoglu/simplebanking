package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.TransactionStatus;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account/v1")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") String accountNumber) {
        return ResponseEntity.of(accountService.findAccount(accountNumber));
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction transaction) {
        return ResponseEntity.of(accountService.credit(accountNumber, transaction.getAmount()));
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction transaction) {
        return ResponseEntity.of(accountService.debit(accountNumber, transaction.getAmount()));
    }

    @PostMapping("/pay-phone-bill/{accountNumber}")
    public ResponseEntity<TransactionStatus> payPhoneBill(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction transaction) {
        return ResponseEntity.of(accountService.payBill(accountNumber, transaction.getAmount()));
    }
}