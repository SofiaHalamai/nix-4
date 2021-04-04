package ua.com.alevel.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.alevel.service.BookService;
import ua.com.alevel.db.DBInMemory;
import ua.com.alevel.entity.Book;

import java.util.Set;

public class BookServiceImpl implements BookService <Book> {

    private static final Logger LOG = LogManager.getLogger(BookServiceImpl.class);
    private final DBInMemory db = DBInMemory.getInstance();

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
    public Set<Book> findAll() {
        LOG.info("Return find all of books");
        return db.findAllBook();
    }
}
