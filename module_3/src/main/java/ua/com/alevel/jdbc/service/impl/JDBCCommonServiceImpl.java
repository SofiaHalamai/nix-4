package ua.com.alevel.jdbc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.hibernate.exception.FinanceResourceNotFoundException;
import ua.com.alevel.jdbc.entity.AccountStatement;
import ua.com.alevel.jdbc.service.JDBCCommonService;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JDBCCommonServiceImpl implements JDBCCommonService {

    public static final Logger logger = LoggerFactory.getLogger(JDBCCommonServiceImpl.class);

    @Override
    public List<AccountStatement> getAllStatementByAccount(int id, String startTime, String finishTime, Connection connection) {
        logger.debug("Start getting all statement by account ID");
        List<AccountStatement> accountList = new ArrayList<>();
        ResultSet result;
        String timeResult;
        long amount;
        try (PreparedStatement prSt = connection.prepareStatement(
                "select t.account_id, t.date, t.amount, i.name_category, e.name_category as name_expense " +
                        "from transactions t " +
                        "left join expenses_transactions et on et.transaction_id=t.id " +
                        "left join incomes_transactions it on it.transaction_id=t.id " +
                        "left join incomes i on i.id = it.income_id " +
                        "left join expenses e on e.id = et.expense_id " +
                        "where account_id = ? and t.date > ? and t.date < ?")) {
            prSt.setInt(1, id);
            prSt.setTimestamp(2, Timestamp.valueOf(startTime));
            prSt.setTimestamp(3, Timestamp.valueOf(finishTime));
            result = prSt.executeQuery();
            while (result.next()) {
                AccountStatement accountStatement = new AccountStatement();
                accountStatement.setAccountId(result.getInt("account_id"));
                timeResult = result.getString("date");
                timeResult = timeResult.substring(0, 19);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                accountStatement.setDate(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.parse(timeResult, formatter)));
                amount = result.getLong("amount");
                accountStatement.setAmount(amount);
                if (amount > 0) {
                    accountStatement.setCategories(Collections.singletonList(result.getString("name_category")));
                } else {
                    accountStatement.setCategories(Collections.singletonList(result.getString("name_expense")));
                }
                accountList.add(accountStatement);
            }
        } catch (SQLException e) {
            logger.error("Error SQL! Error -> Get all statement by Account", e);
        }
        return accountList;
    }

    @Override
    public Integer findTotalIncome(int id, String startTime, String finishTime, Connection connection) {
        logger.debug("Start finding total income");
        ResultSet result;
        Integer totalIncomes = null;
        try (PreparedStatement prSt = connection.prepareStatement(
                "select sum(amount) from transactions t where account_id = ? and t.date > ? and t.date < ? and amount > 0")) {
            prSt.setInt(1, id);
            prSt.setTimestamp(2, Timestamp.valueOf(startTime));
            prSt.setTimestamp(3, Timestamp.valueOf(finishTime));
            result = prSt.executeQuery();
            result.next();
            totalIncomes = result.getInt(1);
        } catch (SQLException e) {
            logger.error("Error SQL! Error -> find total income", e);
        }
        return totalIncomes;
    }

    @Override
    public Integer findBalance(int id, String startTime, String finishTime, Connection connection) {
        logger.debug("Start finding balance");
        ResultSet result;
        Integer balance = null;
        try (PreparedStatement prSt = connection.prepareStatement(
                "select sum(amount) from transactions t where account_id = ? and t.date > ? and t.date < ?")) {
            prSt.setInt(1, id);
            prSt.setTimestamp(2, Timestamp.valueOf(startTime));
            prSt.setTimestamp(3, Timestamp.valueOf(finishTime));
            result = prSt.executeQuery();
            result.next();
            balance = result.getInt(1);
        } catch (SQLException e) {
            logger.error("Error SQL! Error -> find balance", e);
        }
        return balance;
    }

    @Override
    public List<Integer> findNumberAccountByUserID(int id, Connection connection) throws FinanceResourceNotFoundException {
        logger.debug("Start finding number account by user id");
        ResultSet result;
        List <Integer> numbersAccount = new ArrayList<>();
        try (PreparedStatement prSt = connection.prepareStatement(
                "select id from accounts where user_id = ?")) {
            prSt.setInt(1, id);
            result = prSt.executeQuery();
            while (result.next()) {
                numbersAccount.add(result.getInt("id"));
            }
            if (numbersAccount.isEmpty()){
                throw new FinanceResourceNotFoundException(id);
            }
        } catch (SQLException e) {
            logger.error("Error SQL! Error -> find number account by user id", e);
        }
        return numbersAccount;
    }
}
