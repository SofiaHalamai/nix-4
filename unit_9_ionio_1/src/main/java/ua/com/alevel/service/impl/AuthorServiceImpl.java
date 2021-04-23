package ua.com.alevel.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.alevel.db.DBInCSV;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import java.util.List;

public class AuthorServiceImpl implements AuthorService<Author> {

    private static final Logger LOG = LogManager.getLogger(AuthorServiceImpl.class);
    private final DBInCSV db = DBInCSV.getInstance();

    @Override
    public void create(Author author) {
        LOG.info("Start author create");
        db.createAuthor(author);
        LOG.info("Finish author create");
    }

    @Override
    public void update(Author author) {
        LOG.info("Start author update");
        db.updateAuthor(author);
        LOG.info("Finish author update");
    }

    @Override
    public void delete(int id) {
        LOG.info("Start author delete");
        db.deleteAuthor(id);
        LOG.info("Finish author delete");
    }

    @Override
    public Author findById(int id) {
        LOG.info("Return find by ID author");
        return db.findAuthorById(id);
    }

    @Override
    public List<Author> findAll() {
        LOG.info("Return find all of authors");
        return db.findAllAuthor();
    }
    @Override
    public List<Author> findAuthorsByBook(Book book) {
        LOG.info("Return find Authors by book");
        return db.findAuthorsByBook(book);
    }
}
