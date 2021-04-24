package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.BookDao;
import ua.com.alevel.db.DBInCSV;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;


import java.util.List;

public class BookDaoImpl implements BookDao<Book> {

    private final DBInCSV db = DBInCSV.getInstance();

    @Override
    public void create(Book book) {
        db.createBook(book);
    }


    @Override
    public void update(Book book) {
        db.updateBook(book);
    }

    @Override
    public void delete(int id) {
        db.deleteBook(id);
    }

    @Override
    public Book findById(int id) {
        return db.findBookById(id);
    }

    @Override
    public List<Book> findAll() {
        return db.findAllBook();
    }

    @Override
    public List<Book> findBooksByAuthor(Author author) {
        return db.findBooksByAuthor(author);
    }
}

