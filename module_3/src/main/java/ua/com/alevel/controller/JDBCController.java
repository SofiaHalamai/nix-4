package ua.com.alevel.controller;

import ua.com.alevel.jdbc.db.ConnectionToDB;
import ua.com.alevel.jdbc.entity.AccountStatement;
import ua.com.alevel.jdbc.service.JDBCCommonService;
import ua.com.alevel.jdbc.service.impl.JDBCCommonServiceImpl;
import ua.com.alevel.jdbc.util.StatementCreator;
import ua.com.alevel.jdbc.entity.TableCSV;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static ua.com.alevel.controller.MainController.checkCorrectAccountID;
import static ua.com.alevel.hibernate.service.jpa.JPACommonService.logger;

public class JDBCController {

    private static final String filepath = "account_statement.csv";

    public static void runJDBCPartApp(BufferedReader reader) throws IOException {
        int exit;
        ConnectionToDB db = new ConnectionToDB();
        Connection connection = null;
        boolean checkConnectAndInput = true;
        while (checkConnectAndInput) {
            try {
                System.out.println("Enter username db: ");
                String username = reader.readLine();
                System.out.println("Enter password db: ");
                String password = reader.readLine();
                connection = db.getConnectionToDb(username, password);
                checkConnectAndInput = false;
            } catch (Exception e) {
                logger.error("Error connecting to DB! Incorrect password or username! Try again!");
            }
        }

        checkConnectAndInput = true;
        JDBCCommonService service = new JDBCCommonServiceImpl();
        StatementCreator statementCreator = new StatementCreator();
        TableCSV tableCSV = null;
        do {
            while (checkConnectAndInput) {
                try {
                    System.out.println("Enter user id: ");
                    int id = Integer.parseInt(reader.readLine());
                    int accountId;
                    List<Integer> numAccount = service.findNumberAccountByUserID(id, connection);
                    do {
                        System.out.println("This user has such account numbers " + numAccount + "\nChoose one of them: ");
                        accountId = Integer.parseInt(reader.readLine());
                    } while (!checkCorrectAccountID(numAccount, accountId));
                    System.out.println("Enter start date (format yyyy-mm-dd HH:mm:ss): ");
                    String startDate = reader.readLine();
                    System.out.println("Enter finish date (format yyyy-mm-dd HH:mm:ss): ");
                    String finishDate = reader.readLine();
                    List<AccountStatement> accountStatementList = service.getAllStatementByAccount(accountId, startDate, finishDate, connection);
                    tableCSV = statementCreator.creatingContentForFile(accountStatementList,
                            service.findTotalIncome(accountId, startDate, finishDate, connection),
                            service.findBalance(accountId, startDate, finishDate, connection));
                    checkConnectAndInput = false;
                } catch (Exception e) {
                    logger.error("Incorrect input");
                }
            }
            checkConnectAndInput = true;
            statementCreator.writeIntoFile(tableCSV, filepath);
            logger.debug("If you want to end the program press -> 0, if continue -> 1");
            exit = Integer.parseInt(reader.readLine());
        } while (exit == 1);
        try {
            connection.close();
            System.exit(0);
        } catch (SQLException throwables) {
            logger.error("Error closing connection");
        }
    }
}
