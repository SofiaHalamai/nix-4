package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Book extends BaseEntity{

    private String name;
    private List<String> authors = new ArrayList<>();

    public void setAuthors(String author) {
        authors.add(author);
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String toString() {
        return "{Name = '" + name + '\''
                + ", id = " + getId()
                + " , authors = '" + authors + '\''
                +"}";
    }
}
