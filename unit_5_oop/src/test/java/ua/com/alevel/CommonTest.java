package ua.com.alevel;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.impl.AuthorServiceImpl;
import ua.com.alevel.service.impl.BookServiceImpl;

import java.util.Set;

public class CommonTest {

    private static final BookService<Book> bookService = new BookServiceImpl();
    private static final AuthorService<Author> authorService = new AuthorServiceImpl();
    private static final String str = "DEFAULT";

    @BeforeAll
    public static void init() {
        for (int i = 0; i < 10; i++) {
            String str = "test" + i;
            Book book = new Book();
            book.setAuthors(str);
            book.setName(str);
            book.setId(i++);
            bookService.create(book);
            Author author = new Author();
            author.setBooks(str);
            author.setFirstName(str);
            author.setLastName(str);
            author.setId(i++);
            authorService.create(author);
        }
        Assert.assertTrue(CollectionUtils.isNotEmpty(bookService.findAll()));
        Assert.assertTrue(CollectionUtils.isNotEmpty(authorService.findAll()));
    }

    @Test
    @Order(1)
    public void createBookAndAuthor() {
        Set<Book> books = bookService.findAll();
        int previousBook = books.size();

        Author author = new Author();
        author.setBooks(str);
        author.setFirstName(str);
        author.setLastName(str);
        author.setId(12);
        author = authorService.findById(2);

        Assert.assertTrue(author != null);

        Book book = new Book();
        book.setName(str);
        book.setAuthors(str);
        book.setId(17);
        bookService.create(book);
        book = bookService.findById(2);
        Assert.assertTrue(book != null);
        books = bookService.findAll();
        int last = books.size();

        Assert.assertEquals(previousBook + 1, last);
    }

    @Test
    @Order(2)
    public void updateBookAndAuthor() {
        Book book = bookService.findById(2);
        book.setName(str + "1");
        bookService.update(book);
        book = bookService.findById(2);
        Assert.assertEquals(str + "1", book.getName());

        Author author = authorService.findById(3);
        author.setFirstName(str + "2");
        authorService.update(author);
        author = authorService.findById(3);
        Assert.assertEquals(str + "2", author.getFirstName());
    }

    @Test
    @Order(3)
    public void deleteBookAndAuthor() {
        int startSize = bookService.findAll().size();
        bookService.delete(3);
        int afterDeleteSize = bookService.findAll().size();
        Assertions.assertEquals(startSize - 1, afterDeleteSize);
    }


    @Test
    @Order(4)
    public void readBookAndAuthorById() {
        Assertions.assertEquals("test9", bookService.findById(4).getName());
        Assertions.assertEquals("DEFAULT2", authorService.findById(3).getFirstName());
    }

    @Test
    @Order(5)
    public void readAllBooksAndAuthors() {
        Assertions.assertNotNull(bookService.findAll());
        Assertions.assertNotNull(authorService.findAll());
    }
}
