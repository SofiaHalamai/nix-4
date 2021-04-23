package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Author extends BaseEntity{

    private String firstName;
    private String lastName;
    private List<String> books = new ArrayList<>();

    public void setBooks(String book) {
        this.books.add(book);
    }

    public void setBooks(List<String> book) {
        this.books = book;
    }

    public String toString() {
        return "{First name = '" + firstName + '\''
                + ", Last name = '" + lastName + '\''
                + ", Books = '" + books + '\''
                + ", id = " + getId() + "}";
    }
}
