package com.eteration.simplebanking;


import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModelTest {

    @Test
    public void testCreateAccountAndSetBalance0() {
        Account account = new Account("Kerem Karaca", "17892");
        assertTrue(account.getOwner().equals("Kerem Karaca"));
        assertTrue(account.getAccountNumber().equals("17892"));
        assertTrue(account.getBalance() == 0);
    }

	/*
	This test methods commented out because of Anemic Domain Model,

	@Test
	public void testDepositIntoBankAccount() {
		Account account = new Account("Demet Demircan", "9834");
		account.deposit(100);
		assertTrue(account.getBalance() == 100);
	}

	@Test
	public void testWithdrawFromBankAccount() throws InsufficientBalanceException {
		Account account = new Account("Demet Demircan", "9834");
		account.deposit(100);
		assertTrue(account.getBalance() == 100);
		account.withdraw(50);
		assertTrue(account.getBalance() == 50);
	}

	@Test
	public void testWithdrawException() {
		Assertions.assertThrows( InsufficientBalanceException.class, () -> {
			Account account = new Account("Demet Demircan", "9834");
			account.deposit(100);
			account.withdraw(500);
		  });

	}*/

    @Test
    public void testTransactions() throws InsufficientBalanceException {
        // Create account
        Account account = new Account("Canan Kaya", "1234");
        assertTrue(account.getTransactions().size() == 0);

        // Deposit Transaction
        DepositTransaction depositTrx = new DepositTransaction(100);
        //assertTrue(depositTrx.getDate() != null);
        // this assertion commented out because of PrePersist annotation, date will be written when save process
        account.post(depositTrx);
        assertTrue(account.getTransactions().size() == 1);
        assertTrue(account.getTransactions().contains(depositTrx));
        // Withdrawal Transaction
        WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60);
        //assertTrue(withdrawalTrx.getDate() != null);
        account.post(withdrawalTrx);
        assertTrue(account.getTransactions().size() == 2);
        assertTrue(account.getTransactions().contains(withdrawalTrx));
    }
}
