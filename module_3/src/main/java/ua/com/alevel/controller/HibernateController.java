package ua.com.alevel.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.ServiceException;
import ua.com.alevel.hibernate.exception.FinanceDataLayerException;
import ua.com.alevel.hibernate.exception.FinanceResourceNotFoundException;
import ua.com.alevel.hibernate.model.dto.TransactionDto;
import ua.com.alevel.hibernate.model.dto.UserDto;
import ua.com.alevel.hibernate.service.jpa.JPACommonService;
import ua.com.alevel.hibernate.util.ExpenseCategory;
import ua.com.alevel.hibernate.util.IncomeCategory;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static ua.com.alevel.controller.MainController.checkCorrectAccountID;
import static ua.com.alevel.hibernate.service.jpa.JPACommonService.logger;
import static ua.com.alevel.hibernate.util.ConnectToDb.connectingWithPasswordAndLoginToDB;

public class HibernateController {

    static Session session;
    static SessionFactory sessionFactory;

    public static void runHibernatePartApp(BufferedReader reader) throws IOException {
        UserDto userDto = null;
        int exit;
        boolean checkConnectAndID = true;
        while (checkConnectAndID) {
            try {
                sessionFactory = connectingWithPasswordAndLoginToDB(reader).buildSessionFactory();
                checkConnectAndID = false;
            } catch (ServiceException e) {
                logger.error("Error connecting to DB! Incorrect password or username! Try again!");
            }
        }
        checkConnectAndID = true;
        JPACommonService service = new JPACommonService(() -> session = sessionFactory.openSession());
        do {
            while (checkConnectAndID) {
                try {
                    System.out.println("Enter user id: ");
                    int userId = Integer.parseInt(reader.readLine());
                    logger.info("Entered user ID = " + userId);
                    userDto = service.findUserById(userId);
                    checkConnectAndID = false;
                } catch (FinanceResourceNotFoundException e) {
                    logger.error("User not found!");
                }
            }
            checkConnectAndID = true;
            try {
                service.save(createTransactionDTO(userDto, reader));
            } catch (FinanceDataLayerException e) {
                logger.error("Transaction error!");
                sessionFactory.close();
            }
            logger.debug("If you want to end the program press -> 0, if continue -> 1");
            exit = Integer.parseInt(reader.readLine());
        } while (exit == 1);
        sessionFactory.close();
        System.exit(0);
    }

    private static TransactionDto createTransactionDTO(UserDto u, BufferedReader reader){
        var transactionDto = new TransactionDto();
        List<Integer> categories = new ArrayList<>();
        int accountId;
        long money;
        try {
            do {
                System.out.printf("This user has such account numbers %s\nChoose one of them: ", u.getListIdAccounts());
                accountId = Integer.parseInt(reader.readLine());
            } while (!checkCorrectAccountID(u.getListIdAccounts(), accountId));
            transactionDto.setAccountId(accountId);
            logger.info("Entered account ID = " + accountId);
            do {
                System.out.println("Enter the amount of money: ");
                money = Long.parseLong(reader.readLine());
            } while (money == 0);
            transactionDto.setAmount(money);
            logger.info("Entered amount of money = " + money);
            System.out.println("Enter how many categories you want to select: ");
            int amountCat = Integer.parseInt(reader.readLine());
            if (transactionDto.getAmount() > 0) {
                for (IncomeCategory incomeCategory : IncomeCategory.values())
                    System.out.println((incomeCategory.ordinal() + 1) + ". " + incomeCategory);
                for (int i = 0; i < amountCat; i++) {
                    System.out.printf("Enter %d category of income: ", i + 1);
                    categories.add(Integer.valueOf(reader.readLine()));
                }
            } else if (transactionDto.getAmount() < 0) {
                for (ExpenseCategory expenseCategory : ExpenseCategory.values())
                    System.out.println((expenseCategory.ordinal() + 1) + ". " + expenseCategory);
                for (int i = 0; i < amountCat; i++) {
                    System.out.printf("Enter %d the category of expense: ", i + 1);
                    categories.add(Integer.valueOf(reader.readLine()));
                }
            }
            transactionDto.setCategory(categories);
            transactionDto.setDate(Instant.now());
        }catch (Exception e) {
            logger.error("Incorrect input");
        }
        return transactionDto;
    }
}
