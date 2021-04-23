package ua.com.alevel.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.alevel.db.DBInCSV;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService<Book> {

    private static final Logger LOG = LogManager.getLogger(BookServiceImpl.class);
    private final DBInCSV db = DBInCSV.getInstance();

    @Override
    public void create(Book book) {
        LOG.info("Start book create");
        db.createBook(book);
        LOG.info("Finish book create");

    }


    @Override
    public void update(Book book) {
        LOG.info("Start book update");
        db.updateBook(book);
        LOG.info("Finish book update");

    }

    @Override
    public void delete(int id) {
        LOG.info("Start book delete");
        db.deleteBook(id);
        LOG.info("Finish book delete");

    }

    @Override
    public Book findById(int id) {
        LOG.info("Return find by ID book");
        return db.findBookById(id);
    }

    @Override
    public List<Book> findAll() {
        LOG.info("Return find all of books");
        return db.findAllBook();
    }
    @Override
    public List<Book> findBooksByAuthor(Author author) {
        LOG.info("Return find books by author");
        return db.findBooksByAuthor(author);
    }
}
