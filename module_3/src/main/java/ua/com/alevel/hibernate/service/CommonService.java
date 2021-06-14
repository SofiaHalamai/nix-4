package ua.com.alevel.hibernate.service;


import ua.com.alevel.hibernate.exception.FinanceDataLayerException;
import ua.com.alevel.hibernate.exception.FinanceResourceNotFoundException;
import ua.com.alevel.hibernate.model.dto.TransactionDto;
import ua.com.alevel.hibernate.model.dto.UserDto;

public interface CommonService {

    void save(TransactionDto transactionDto) throws FinanceDataLayerException;
    UserDto findUserById(int id) throws FinanceResourceNotFoundException;

}
