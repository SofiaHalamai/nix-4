package ua.com.alevel.hibernate.exception;

public class FinanceResourceNotFoundException extends FinanceDataAccessException {

    public FinanceResourceNotFoundException(Integer id) {
        super("Resource with id = " + id + " was not found!");
    }

}
