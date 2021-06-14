package ua.com.alevel.hibernate.service.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.hibernate.exception.FinanceDataLayerException;
import ua.com.alevel.hibernate.exception.FinanceResourceNotFoundException;
import ua.com.alevel.hibernate.model.dto.TransactionDto;
import ua.com.alevel.hibernate.model.dto.UserDto;
import ua.com.alevel.hibernate.model.entity.*;
import ua.com.alevel.hibernate.service.CommonService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class JPACommonService implements CommonService {

    public static Logger logger = LoggerFactory.getLogger(JPACommonService.class);

    private final Supplier<EntityManager> entityManager;

    public JPACommonService(Supplier<EntityManager> persistence) {
        this.entityManager = persistence;
    }

    @Override
    public void save(TransactionDto transactionDto) throws FinanceDataLayerException {
        logger.debug("Start save transaction!");
        EntityManager jpa = entityManager.get();
        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();

        try {
            logger.debug("Start merge entity with TransactionDto!");
            var entity = new Transaction();
            entity.setAccount(jpa.find(Account.class, transactionDto.getAccountId()));
            entity.setDate(transactionDto.getDate());
            entity.setAmount(transactionDto.getAmount());
            for (int i = 0; i < transactionDto.getCategory().size(); i++) {
                if (transactionDto.getAmount() > 0) {
                    entity.addIncomeCategories(jpa.find(IncomeCategory.class, transactionDto.getCategory().get(i)));
                } else if (transactionDto.getAmount() < 0) {
                    entity.addExpenseCategories(jpa.find(ExpenseCategory.class, transactionDto.getCategory().get(i)));
                }
            }
            logger.debug("Entity merged with TransactionDto!");
            jpa.persist(entity);
            transaction.commit();
            logger.debug("Transaction saved");
        } catch (RuntimeException e) {
            logger.error("Data layer operation failed", e);
            transaction.rollback();
            throw new FinanceDataLayerException("Data layer operation failed");
        }
    }

    @Override
    public UserDto findUserById(int id) throws FinanceResourceNotFoundException {
        logger.debug("Start find user by id!");
        EntityManager jpa = entityManager.get();

        User user = jpa.find(User.class, id);
        if (user == null) {
            throw new FinanceResourceNotFoundException(id);
        }
        logger.debug("User found!");
        return entityUserToDto(user);
    }

    private static UserDto entityUserToDto(User entity) {
        List<Integer> listIdAccounts = new ArrayList<>();
        int size = entity.getAccounts().size();
        for (int i = 0; i < size; i++) {
            listIdAccounts.add(entity.getAccounts().get(i).getId());
        }

        return new UserDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                listIdAccounts
        );
    }
}
