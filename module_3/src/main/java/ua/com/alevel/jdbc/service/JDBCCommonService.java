package ua.com.alevel.jdbc.service;

import ua.com.alevel.hibernate.exception.FinanceResourceNotFoundException;
import ua.com.alevel.jdbc.entity.AccountStatement;

import java.sql.Connection;
import java.util.List;

public interface JDBCCommonService {

    List<AccountStatement> getAllStatementByAccount(int id, String startTime, String finishTime, Connection connection);

    Integer findTotalIncome (int id, String startTime, String finishTime, Connection connection);

    Integer findBalance (int id, String startTime, String finishTime, Connection connection);

    List <Integer> findNumberAccountByUserID (int id, Connection connection) throws FinanceResourceNotFoundException;
}
