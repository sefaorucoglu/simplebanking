package com.eteration.simplebanking;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner loadData(AccountRepository accountRepository) {
        return args -> {
            if (accountRepository.count() == 0) {
                Account acc1 = new Account("Kerem Karaca", "17892");
                acc1.setBalance(950.0);
                acc1.post(new DepositTransaction(1000.0));
                Account acc2 = new Account("Test Account", "6697788");
                acc2.setBalance(2000.0);
                accountRepository.saveAllAndFlush(List.of(acc1, acc2));
            }
        };
    }

}
