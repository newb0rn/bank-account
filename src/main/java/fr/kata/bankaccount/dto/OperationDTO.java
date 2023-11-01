package fr.kata.bankaccount.dto;

import lombok.Data;

/**
 * DTO representing an operation.
 */
@Data
public class OperationDTO {

    private String label;

    private String date;

    private Double amount;

    private String accountNumber;

    public OperationDTO(String label, Double amount, String date, String accountNumber) {
        this.label = label;
        this.amount = amount;
        this.date = date;
        this.accountNumber = accountNumber;
    }

}
