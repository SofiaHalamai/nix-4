package ua.com.alevel.hibernate.model.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column (nullable = false)
    private Instant date;

    @Column (nullable = false)
    private Long amount;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "transactions")
    private List<IncomeCategory> incomeCategories;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "transactions")
    private List<ExpenseCategory> expenseCategories;

    public Transaction() {
        this.incomeCategories = new ArrayList<>();
        this.expenseCategories = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void addIncomeCategories(IncomeCategory incomeCategory) {
        incomeCategories.add(incomeCategory);
        incomeCategory.getTransactions().add(this);
    }

    public void addExpenseCategories(ExpenseCategory expenseCategory) {
        expenseCategories.add(expenseCategory);
        expenseCategory.getTransactions().add(this);
    }
}
