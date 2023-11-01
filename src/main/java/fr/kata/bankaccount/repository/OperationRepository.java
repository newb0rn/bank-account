package fr.kata.bankaccount.repository;

import fr.kata.bankaccount.model.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing operations
 */
@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {

    /**
     * Retrieve a list of operations for a specific account by its account number
     *
     * @param accountNumber The account number for which to retrieve operations
     * @return List<Operation> the operations associated with the specified account number
     */
    List<Operation> findAllByAccount_AccountNumber(String accountNumber);

}
