package ua.com.alevel.hibernate.exception;

public abstract class FinanceDataAccessException extends Exception {

    public FinanceDataAccessException(String message) {
        super(message);
    }

}
