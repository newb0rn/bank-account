package fr.kata.bankaccount.service;

import fr.kata.bankaccount.dto.OperationDTO;
import fr.kata.bankaccount.dto.PrintingStatementDTO;
import fr.kata.bankaccount.model.Account;
import fr.kata.bankaccount.model.Operation;
import fr.kata.bankaccount.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing operations
 */
@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    /**
     * Adds a new operation to the specified account and returns the corresponding OperationDTO
     * @param account the account where the operation will be added
     * @param operationDTO the operation to be added
     * @return OperationDTO the added operation
     */
    public OperationDTO addOperation(Account account, OperationDTO operationDTO) {
        Operation newOperation = new Operation(operationDTO.getLabel(), new Date(), operationDTO.getAmount(), account);
        Operation savedOperation = operationRepository.save(newOperation);
        return savedOperation.toDTO();
    }

    /**
     * Retrieves a complete list of operations and the balance for a specific account
     * @param accountNumber the account number for which to retrieve the operations and balance
     * @return PrintingStatementDTO containing the list of operations and the account's balance
     */
    public PrintingStatementDTO getAllOperationForAccount(String accountNumber) {
        PrintingStatementDTO printingStatementDTO = new PrintingStatementDTO();
        List<OperationDTO> operations = operationRepository.findAllByAccount_AccountNumber(accountNumber)
                .stream().map(Operation::toDTO).collect(Collectors.toList());
        printingStatementDTO.setOperations(operations);
        printingStatementDTO.setBalance(getBalance(operations));
        return printingStatementDTO;
    }

    /**
     * Sums all operations amount to obtain the balance of the account
     * @param operations all the operations of the account to sum
     * @return Double balance of all operations
     */
    private static Double getBalance(List<OperationDTO> operations) {
        return operations.stream().mapToDouble(OperationDTO::getAmount).sum();
    }
}
