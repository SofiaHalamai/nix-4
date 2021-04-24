package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.db.DBInCSV;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.List;

public class AuthorDaoImpl implements AuthorDao<Author> {

    private final DBInCSV db = DBInCSV.getInstance();

    @Override
    public void create(Author author) {
        db.createAuthor(author);
    }

    @Override
    public void update(Author author) {
        db.updateAuthor(author);
    }

    @Override
    public void delete(int id) {
        db.deleteAuthor(id);
    }

    @Override
    public Author findById(int id) {
        return db.findAuthorById(id);
    }

    @Override
    public List<Author> findAll() {
        return db.findAllAuthor();
    }

    @Override
    public List<Author> findAuthorsByBook(Book book) {
        return db.findAuthorsByBook(book);
    }
}
