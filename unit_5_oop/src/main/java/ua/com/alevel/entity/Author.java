package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class Author extends BaseEntity{

    private String firstName;
    private String lastName;
    Set<String> books = new HashSet<>();

    public String toString() {
        return "{First name = '" + firstName + '\''
                + ", Last name = '" + lastName + '\''
                + ", Books = '" + books + '\''
                + ", id = " + getId() + "}";
    }

    public void setBooks(String book) {
        books.add(book);
    }

    public void setBooks(Set<String> book) {
        books = book;
    }
}
