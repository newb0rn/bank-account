package fr.kata.bankaccount.model;

import fr.kata.bankaccount.dto.OperationDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an operation of an account
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "OPERATION")
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operation")
    private Integer idOperation;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "account_number", referencedColumnName = "account_number", nullable = false)})
    private Account account;

    public Operation(String label, Date date, Double amount, Account account) {
        this.label = label;
        this.date = date;
        this.amount = amount;
        this.account = account;
    }

    /**
     * Convert the model {@link Operation} to {@link fr.kata.bankaccount.dto.OperationDTO}
     *
     * @return {@link fr.kata.bankaccount.dto.OperationDTO} corresponding
     */
    public OperationDTO toDTO() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return new OperationDTO(
                this.label,
                this.amount,
                format.format(this.date),
                this.account.getAccountNumber()
        );
    }
}
