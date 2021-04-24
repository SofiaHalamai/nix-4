package ua.com.alevel.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.impl.BookDaoImpl;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService<Book> {

    private final BookDao bookDao = new BookDaoImpl();
    private static final Logger LOG = LogManager.getLogger(BookServiceImpl.class);

    @Override
    public void create(Book book) {
        LOG.info("Start book create");
        bookDao.create(book);
        LOG.info("Finish book create");
    }


    @Override
    public void update(Book book) {
        if (existBook(book.getId())) {
            LOG.info("Start book update");
            bookDao.update(book);
            LOG.info("Finish book update");
        } else
            LOG.error("Incorrect ID! No such ID!");
    }

    @Override
    public void delete(int id) {
        if (existBook(id)) {
            LOG.info("Start book delete");
            bookDao.delete(id);
            LOG.info("Finish book delete");
        } else
            LOG.error("Incorrect ID! No such ID!");
    }

    @Override
    public Book findById(int id) {
        if (existBook(id)) {
            LOG.info("Return find by ID book");
            return bookDao.findById(id);
        } else {
            LOG.error("Incorrect ID! No such ID!");
            return null;
        }
    }

    @Override
    public List<Book> findAll() {
        LOG.info("Return find all of books");
        return bookDao.findAll();
    }

    @Override
    public List<Book> findBooksByAuthor(Author author) {
        LOG.info("Return find books by author");
        return bookDao.findBooksByAuthor(author);
    }

    private boolean existBook(int id) {
        return bookDao.findById(id) != null;
    }
}
