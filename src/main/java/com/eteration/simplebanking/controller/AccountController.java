package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.TransactionRequestDto;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.TransactionStatus;
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
        return ResponseEntity.ok(accountService.findAccount(accountNumber));
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber,
                                                    @RequestBody TransactionRequestDto transactionRequestDto) {
        return accountService.credit(accountNumber, transactionRequestDto);
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber,
                                                   @RequestBody TransactionRequestDto transactionRequestDto)
            throws InsufficientBalanceException {
        return accountService.debit(accountNumber, transactionRequestDto);
    }

    @PostMapping("/pay-phone-bill/{accountNumber}")
    public ResponseEntity<TransactionStatus> payPhoneBill(@PathVariable String accountNumber,
                                                          @RequestBody TransactionRequestDto transactionRequestDto)
            throws InsufficientBalanceException {
        return accountService.payBill(accountNumber, transactionRequestDto);
    }
}