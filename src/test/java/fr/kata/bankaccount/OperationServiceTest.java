package fr.kata.bankaccount;

import fr.kata.bankaccount.dto.OperationDTO;
import fr.kata.bankaccount.dto.PrintingStatementDTO;
import fr.kata.bankaccount.model.Account;
import fr.kata.bankaccount.model.Operation;
import fr.kata.bankaccount.repository.OperationRepository;
import fr.kata.bankaccount.service.OperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OperationServiceTest {

    @InjectMocks
    private OperationService operationService;

    @Mock
    private OperationRepository operationRepository;

    private List<Operation> operations;
    private Account account;

    @BeforeEach
    void setUp() {
        //Création des JDD pour le test
        account = new Account();
        account.setAccountId(1);
        account.setAccountNumber("accountNb");
        Operation operation1 = new Operation();
        operation1.setIdOperation(1);
        operation1.setAmount(100.0);
        operation1.setLabel("Deposit");
        operation1.setDate(new Date());
        operation1.setAccount(account);
        Operation operation2 = new Operation();
        operation2.setIdOperation(2);
        operation2.setAmount(-50.0);
        operation2.setLabel("Withdrawal");
        operation2.setDate(new Date());
        operation2.setAccount(account);
        operations = new ArrayList<>(Arrays.asList(operation1, operation2));
    }

    @Test
    void getAllOperationForAccountWithOperations() {
        when(operationRepository.findAllByAccount_AccountNumber(anyString())).thenReturn(operations);
        PrintingStatementDTO printingStatementDTO = operationService.getAllOperationForAccount("accountNb");
        Assertions.assertEquals(50.0, printingStatementDTO.getBalance());
        Assertions.assertEquals(2, printingStatementDTO.getOperations().size());
    }

    @Test
    void getAllOperationForAccountWithNoOperation() {
        when(operationRepository.findAllByAccount_AccountNumber(anyString())).thenReturn(new ArrayList<>());
        PrintingStatementDTO printingStatementDTO = operationService.getAllOperationForAccount("accountNb");
        Assertions.assertEquals(0.0, printingStatementDTO.getBalance());
        Assertions.assertEquals(0, printingStatementDTO.getOperations().size());
    }

    @Test
    void addOperation() {
        OperationDTO operationDTO = new OperationDTO("label", 1.0, "date", "accountNb");
        Operation operation = new Operation();
        operation.setLabel("label");
        operation.setDate(new Date());
        operation.setAmount(1.0);
        operation.setAccount(account);
        when(operationRepository.save(any(Operation.class))).thenReturn(operation);
        OperationDTO operationDTOadded = operationService.addOperation(account, operationDTO);
        verify(operationRepository, times(1)).save(Mockito.any(Operation.class));
        Assertions.assertEquals("accountNb", operationDTOadded.getAccountNumber());
        Assertions.assertEquals(1.0, operationDTOadded.getAmount());
    }

}
