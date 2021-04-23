package ua.com.alevel;

import org.apache.commons.collections.CollectionUtils;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Assertions.assertTrue(CollectionUtils.isNotEmpty(bookService.findAll()));
        Assertions.assertTrue(CollectionUtils.isNotEmpty(authorService.findAll()));
    }
    @Test
    @Order(1)
    public void createAuthor() {
        List<Author> authors = authorService.findAll();
        int previousBook = authors.size();

        Author author = new Author();
        author.setBooks(str);
        author.setFirstName(str);
        author.setLastName(str);
        author.setId(12);
        authorService.create(author);
        author = authorService.findById(4);
        Assertions.assertNotNull(author);
        authors = authorService.findAll();
        int last = authors.size();
        assertEquals(previousBook + 1, last);
    }

    @Test
    @Order(2)
    public void createBook() {
        List<Book> books = bookService.findAll();
        int previousBook = books.size();
        Book book = new Book();
        book.setName(str);
        book.setAuthors(str);
        book.setId(17);
        bookService.create(book);
        book = bookService.findById(2);
        Assertions.assertNotNull(book);

        books = bookService.findAll();
        int last = books.size();
        assertEquals(previousBook + 1, last);
    }

    @Test
    @Order(3)
    public void updateBook() {
        Book book = bookService.findById(2);
        book.setName(str + "1");
        bookService.update(book);
        book = bookService.findById(2);
        assertEquals(str + "1", book.getName());
    }

    @Test
    @Order(4)
    public void updateAuthor() {
        Author author = authorService.findById(3);
        author.setFirstName(str + "2");
        authorService.update(author);
        author = authorService.findById(3);
        assertEquals(str + "2", author.getFirstName());
    }

    @Test
    @Order(5)
    public void deleteBook() {
        int startSize = bookService.findAll().size();
        bookService.delete(3);
        int afterDeleteSize = bookService.findAll().size();
        assertEquals(startSize - 1, afterDeleteSize);
    }

    @Test
    @Order(6)
    public void deleteAuthor() {
        int startSize = authorService.findAll().size();
        authorService.delete(2);
        int afterDeleteSize = authorService.findAll().size();
        assertEquals(startSize - 1, afterDeleteSize);
    }

    @Test
    @Order(7)
    public void readBookById() {
        int id = 4;
        String expected = "test9";
        assertEquals(expected, bookService.findById(id).getName());
    }

    @Test
    @Order(8)
    public void readAuthorById() {
        int id = 3;
        String expected = "DEFAULT2";
        assertEquals(expected, authorService.findById(id).getFirstName());
    }

    @Test
    @Order(9)
    public void readAllBooks() {
        Assertions.assertNotNull(bookService.findAll());
    }

    @Test
    @Order(10)
    public void readAllAuthors() {
        Assertions.assertNotNull(authorService.findAll());
    }
}
