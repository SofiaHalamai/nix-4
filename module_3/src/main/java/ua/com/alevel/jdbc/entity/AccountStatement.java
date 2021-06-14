package ua.com.alevel.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

public class AccountStatement {

    private Integer accountId;
    private String date;
    private Long amount;
    private List<String> categories = new ArrayList<>();

    public void addCategory (String category){
        categories.add(category);
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "AccountStatement{" +
                "accountId=" + accountId +
                ", date=" + date +
                ", amount=" + amount +
                ", categories=" + categories +
                '}';
    }
}
