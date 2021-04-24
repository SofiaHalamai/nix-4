package ua.com.alevel.dao;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.List;

public interface BookDao <B extends Book> {

    void create(B b);
    void update(B b);
    void delete(int id);
    B findById(int id);
    List<B> findAll();
    List<B> findBooksByAuthor(Author author);

}
