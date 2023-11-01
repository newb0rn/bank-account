package fr.kata.bankaccount.dto;

import lombok.Data;

import java.util.List;

/**
 * DTO representing a printing statement composed by the operations and the balance.
 */
@Data
public class PrintingStatementDTO {

    private List<OperationDTO> operations;
    private Double balance;
}
