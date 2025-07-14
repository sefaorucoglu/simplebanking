package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.dto.TransactionRequestDto;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.TransactionStatus;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests {

    @Autowired
    private AccountController controller;

    @SpyBean
    private AccountService service;

    @MockBean
    private AccountRepository repository;


    @Test
    public void givenId_Credit_thenReturnJson() throws Exception {

        doReturn(new ResponseEntity<>(new TransactionStatus("OK", "test123"), HttpStatus.OK))
                .when(service).credit(eq("17893"), any(TransactionRequestDto.class));

        ResponseEntity<TransactionStatus> result = controller.credit("17893", new TransactionRequestDto(1000.0));

        assertNotNull(result);
        assertEquals("OK", result.getBody().getStatus());

        verify(service, times(1)).credit(eq("17893"), any(TransactionRequestDto.class));
    }

    @Test
    public void givenId_CreditAndThenDebit_thenReturnJson() throws Exception {
        Account account = new Account("Kerem Karaca", "17892");

        when(repository.findByAccountNumber("17892"))
                .thenReturn(Optional.of(account));

        ResponseEntity<TransactionStatus> result = controller.credit("17892", new TransactionRequestDto(1000.0));
        ResponseEntity<TransactionStatus> result2 = controller.debit("17892", new TransactionRequestDto(50.0));

        assertNotNull(result.getBody());
        assertNotNull(result2.getBody());
        verify(service, times(2)).findAccount("17892");
        assertEquals("OK", result.getBody().getStatus());
        assertEquals("OK", result2.getBody().getStatus());
        assertEquals(950.0, account.getBalance(), 0.001);
    }

    @Test
    public void givenId_CreditAndThenDebitMoreGetException_thenReturnJson()
            throws Exception {
        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            Account account = new Account("Kerem Karaca", "17892");

            doReturn(account).when(service).findAccount("17892");
            ResponseEntity<TransactionStatus> result = controller.credit("17892", new TransactionRequestDto(1000.0));
            assertEquals("OK", result.getBody().getStatus());
            assertEquals(1000.0, account.getBalance(), 0.001);
            verify(service, times(1)).findAccount("17892");

            ResponseEntity<TransactionStatus> result2 = controller.debit("17892", new TransactionRequestDto(5000.0));
        });
    }

    @Test
    public void givenId_GetAccount_thenReturnJson()
            throws Exception {

        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount("17892");
        ResponseEntity<Account> result = controller.getAccount("17892");
        verify(service, times(1)).findAccount("17892");
        assertEquals(account, result.getBody());
    }

}
