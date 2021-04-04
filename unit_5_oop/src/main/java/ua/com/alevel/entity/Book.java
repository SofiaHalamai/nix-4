package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
public class Book extends BaseEntity{

    private String name;
    private Set<String> authors = new HashSet<>();

    public String toString() {
        return "{Name = '" + name + '\''
                + ", id = " + getId()
                + " , authors = '" + authors + '\''
                +"}";
    }

    public void setAuthors(String author) {
        authors.add(author);
    }

    public void setAuthors(Set<String> authors) {
        authors = authors;
    }
}
