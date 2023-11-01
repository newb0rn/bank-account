package fr.kata.bankaccount;

import fr.kata.bankaccount.exception.AccountException;
import fr.kata.bankaccount.model.Account;
import fr.kata.bankaccount.model.Operation;
import fr.kata.bankaccount.repository.AccountRepository;
import fr.kata.bankaccount.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    private Account account;

    @BeforeEach
    void setUp() {
        //Création des JDD pour le test
        account = new Account();
        account.setAccountId(1);
        account.setAccountNumber("accountNb");
    }

    @Test
    void getExistingAccount() throws AccountException {
        when(accountRepository.findByAccountNumber("accountNb")).thenReturn(Optional.ofNullable(account));
        Account accountTest = accountService.getAccount("accountNb");
        Assertions.assertNotNull(accountTest);
        Assertions.assertEquals(1, accountTest.getAccountId());
    }

    @Test
    void getAccountNotFound() {
        when(accountRepository.findByAccountNumber("NoAccount")).thenReturn(Optional.empty());

        Assertions.assertThrows(AccountException.class, () -> {
            accountService.getAccount("NoAccount");
        });
    }

}
