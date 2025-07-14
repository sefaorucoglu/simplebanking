package com.eteration.simplebanking;

import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
class DemoApplicationTests {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    void contextLoads() {
        assertNotNull(accountRepository);
        assertNotNull(accountService);
    }

}
