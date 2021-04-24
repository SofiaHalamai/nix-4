package ua.com.alevel.dao;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.List;

public interface AuthorDao <A extends Author>{

    void create(A a);
    void update(A a);
    void delete(int id);
    A findById(int id);
    List<A> findAll();
    List<A> findAuthorsByBook(Book book);
}
