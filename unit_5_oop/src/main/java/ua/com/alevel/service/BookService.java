package ua.com.alevel.service;

import ua.com.alevel.entity.Book;

import java.util.Set;

public interface BookService <B extends Book> {

    void create(B b);
    void update(B b);
    void delete(int id);
    B findById(int id);
    Set<B> findAll();
}
