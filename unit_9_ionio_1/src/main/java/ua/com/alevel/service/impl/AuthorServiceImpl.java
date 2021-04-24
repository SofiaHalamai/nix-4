package ua.com.alevel.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.impl.AuthorDaoImpl;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import java.util.List;

public class AuthorServiceImpl implements AuthorService<Author> {

    private final AuthorDao authorDao = new AuthorDaoImpl();
    private static final Logger LOG = LogManager.getLogger(AuthorServiceImpl.class);

    @Override
    public void create(Author author) {
        LOG.info("Start author create");
        authorDao.create(author);
        LOG.info("Finish author create");
    }

    @Override
    public void update(Author author) {
        if (existAuthor(author.getId())) {
            LOG.info("Start author update");
            authorDao.update(author);
            LOG.info("Finish author update");
        }else
            LOG.error("Incorrect ID! No such ID!");
    }

    @Override
    public void delete(int id) {
        if (existAuthor(id)) {
            LOG.info("Start author delete");
            authorDao.delete(id);
            LOG.info("Finish author delete");
        }else
            LOG.error("Incorrect ID! No such ID!");
    }

    @Override
    public Author findById(int id) {
        if (existAuthor(id)) {
            LOG.info("Return find by ID author");
            return authorDao.findById(id);
        }else {
            LOG.error("Incorrect ID! No such ID!");
            return null;
        }
    }

    @Override
    public List<Author> findAll() {
        LOG.info("Return find all of authors");
        return authorDao.findAll();
    }
    @Override
    public List<Author> findAuthorsByBook(Book book) {
        LOG.info("Return find Authors by book");
        return authorDao.findAuthorsByBook(book);
    }

    private boolean existAuthor(int id) {
        return authorDao.findById(id) != null;
    }
}
