package fr.kata.bankaccount;

import fr.kata.bankaccount.controller.OperationController;
import fr.kata.bankaccount.dto.OperationDTO;
import fr.kata.bankaccount.dto.PrintingStatementDTO;
import fr.kata.bankaccount.exception.AccountException;
import fr.kata.bankaccount.model.Account;
import fr.kata.bankaccount.service.AccountService;
import fr.kata.bankaccount.service.OperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.when;

@SpringBootTest
public class OperationControllerTest {

    @InjectMocks
    private OperationController operationController;
    @Mock
    private OperationService operationService;
    @Mock
    private AccountService accountService;
    private OperationDTO operationDTO;

    @BeforeEach
    void setUp() {
        operationDTO = new OperationDTO("Deposit", 10.0, null, "accountNb");
    }

    @Test
    void addOperationOK() throws AccountException {
        Account account = new Account();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = format.format(new Date());
        when(accountService.getAccount(operationDTO.getAccountNumber())).thenReturn(account);
        when(operationService.addOperation(account, operationDTO)).thenReturn(
                new OperationDTO("Deposit", 10.0, date, "accountNb"));
        ResponseEntity<String> response = operationController.addOperation(operationDTO);
        Assertions.assertEquals(201, response.getStatusCodeValue());
        Assertions.assertEquals("10.0", response.getBody());
    }

    @Test
    void addOperationAccountError() throws AccountException {
        when(accountService.getAccount(operationDTO.getAccountNumber())).thenThrow(
                new AccountException("The account " + operationDTO.getAccountNumber() + " doesn't exist"));
        ResponseEntity<String> response = operationController.addOperation(operationDTO);
        Assertions.assertEquals(400, response.getStatusCodeValue());
        Assertions.assertEquals("The account accountNb doesn't exist", response.getBody());
    }

    @Test
    void addOperationDepositError() throws AccountException {
        operationDTO.setAmount(-50.0);
        Account account = new Account();
        when(accountService.getAccount(operationDTO.getAccountNumber())).thenReturn(account);
        ResponseEntity<String> response = operationController.addOperation(operationDTO);
        Assertions.assertEquals(400, response.getStatusCodeValue());
        Assertions.assertEquals("A deposit cannot be null or negative", response.getBody());
    }

    @Test
    void addOperationWrongDepositFormat() throws AccountException {
        operationDTO.setAmount(null);
        Account account = new Account();
        when(accountService.getAccount(operationDTO.getAccountNumber())).thenReturn(account);
        ResponseEntity<String> response = operationController.addOperation(operationDTO);
        Assertions.assertEquals(400, response.getStatusCodeValue());
        Assertions.assertEquals("Your deposit must be a number", response.getBody());
    }

    @Test
    void getAllOperations() {
        when(operationService.getAllOperationForAccount("accountNb")).thenReturn(new PrintingStatementDTO());
        ResponseEntity<PrintingStatementDTO> response = operationController.getAllOperations("accountNb");
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

}
