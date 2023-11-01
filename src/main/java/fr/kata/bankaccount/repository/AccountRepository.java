package fr.kata.bankaccount.repository;

import fr.kata.bankaccount.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing accounts
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    /**
     * Retrieve an account by its account number
     *
     * @param accountNumber The account number to search for
     * @return An Optional containing the account with the specified account number if it exists, or an empty Optional if not found
     */
    Optional<Account> findByAccountNumber(String accountNumber);

}
