package fr.kata.bankaccount.exception;

/**
 * Exception class for account-related errors
 */
public class AccountException extends Exception {

    /**
     * Constructs an AccountException with the specified error message
     *
     * @param message The error message describing the account-related issue
     */
    public AccountException(String message) {
        super(message);
    }
}
