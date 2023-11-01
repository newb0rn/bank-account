package fr.kata.bankaccount.controller;

import fr.kata.bankaccount.dto.OperationDTO;
import fr.kata.bankaccount.dto.PrintingStatementDTO;
import fr.kata.bankaccount.exception.AccountException;
import fr.kata.bankaccount.model.Account;
import fr.kata.bankaccount.model.Operation;
import fr.kata.bankaccount.service.AccountService;
import fr.kata.bankaccount.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for managing operations and account information.
 */
@Controller
@RequestMapping("/account")
public class OperationController {

    @Autowired
    AccountService accountService;

    @Autowired
    OperationService operationService;

    /**
     * Add a new operation to the account
     * @param newOperation operation data to be added
     * @return ResponseEntity with a status code and an error message if needed
     */
    @PostMapping(value = "/newOperation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> addOperation(@RequestBody final OperationDTO newOperation) {

        Account account;
        try {
            account = accountService.getAccount(newOperation.getAccountNumber());
        } catch (AccountException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if (newOperation.getAmount() == null) {
            return new ResponseEntity<>("Your deposit must be a number", HttpStatus.BAD_REQUEST);
        }

        if (newOperation.getLabel().equals("Deposit") && newOperation.getAmount() <= 0) {
            return new ResponseEntity<>("A deposit cannot be null or negative", HttpStatus.BAD_REQUEST);
        }

        OperationDTO operationDTO = operationService.addOperation(account, newOperation);

        return new ResponseEntity<>(operationDTO.getAmount().toString(), HttpStatus.CREATED);
    }

    /**
     * Retrieves an account's complete list of operations
     * @param accountNumber the account number for which to retrieve the operations
     * @return PrintingStatementDTO which is composed of the operations and the balance
     */
    @GetMapping(value = "/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PrintingStatementDTO> getAllOperations(@PathVariable("accountNumber") String accountNumber) {
        PrintingStatementDTO printingStatementDTO = operationService.getAllOperationForAccount(accountNumber);
       return new ResponseEntity<>(printingStatementDTO, HttpStatus.OK);
    }

}
