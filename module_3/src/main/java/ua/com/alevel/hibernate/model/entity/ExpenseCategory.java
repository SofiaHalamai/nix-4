package ua.com.alevel.hibernate.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "expenses")
public class ExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "name_category")
    private String nameCategory;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "expense_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    )
    private List<Transaction> transactions;

    public ExpenseCategory() {
        this.transactions = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
