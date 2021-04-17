package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Setter
@Getter
@ToString
public class Book implements Comparable<Book> {

    private String name;
    private Integer pages;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Book book = (Book) obj;
        return Objects.equals(name, book.name) && Objects.equals(pages, book.pages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pages);
    }

    @Override
    public int compareTo(Book o) {
        int result = this.name.compareTo(o.name);
        if (result == 0) {
            result = this.pages.compareTo(o.pages);
        }
        return result;
    }


}

