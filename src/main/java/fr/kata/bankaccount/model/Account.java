package fr.kata.bankaccount.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Represents an account.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "ACCOUNT")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Integer accountId;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

}

