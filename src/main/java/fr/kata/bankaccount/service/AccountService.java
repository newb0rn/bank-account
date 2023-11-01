package fr.kata.bankaccount.service;

import fr.kata.bankaccount.exception.AccountException;
import fr.kata.bankaccount.model.Account;
import fr.kata.bankaccount.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for managing accounts
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Retrieves an account by its account number
     * @param accountNumber the account number
     * @return Account the corresponding account
     * @throws AccountException if the account doesn't exist
     */
    public Account getAccount(String accountNumber) throws AccountException {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);

        if(!optionalAccount.isPresent()) {
            throw new AccountException("The account " + accountNumber + " doesn't exist");
        }

        return optionalAccount.get();
    }
}
