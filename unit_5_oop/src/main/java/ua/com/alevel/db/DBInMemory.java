package ua.com.alevel.db;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.*;

public class DBInMemory {

    private Set<Author> authors;
    private Set<Book> books;

    private static DBInMemory db;

    private DBInMemory(){
        this.authors = new HashSet<>();
        this.books = new HashSet<>();
    }

    public static DBInMemory getInstance(){
        if (db == null) {
            db = new DBInMemory();
        }
        return db;
    }

    public void createAuthor (Author author){
        author.setId(authors.size() + 1);
        authors.add(author);
    }

    public void createBook (Book book){
        book.setId(books.size() + 1);
        books.add(book);
    }

    public void updateBook(Book book){
        Book currentBook = findBookById(book.getId());
        currentBook.setName(book.getName());
        currentBook.setAuthors(book.getAuthors());
    }

    public void updateAuthor(Author author){
       Author currentAuthor = findAuthorById(author.getId());
       currentAuthor.setFirstName(author.getFirstName());
       currentAuthor.setLastName(author.getLastName());
       currentAuthor.setBooks(author.getBooks());
    }

    public void deleteBook(int id){
        books.remove(findBookById(id));
    }

    public void deleteAuthor(int id){
        authors.remove(findAuthorById(id));
    }

    public Set<Book> findAllBook() {
        return books;
    }

    public Set<Author> findAllAuthor() {
        return authors;
    }

    public Book findBookById (int id) {
        Book current = books.stream().filter(b -> id == b.getId()).findFirst().orElse(null);
        if (current == null) {
            throw new RuntimeException("Book not found");
        }
        return current;
    }

    public Author findAuthorById (int id) {
        Author current = authors.stream().filter(a -> id == a.getId()).findFirst().orElse(null);
        if (current == null) {
            throw new RuntimeException("Author not found");
        }
        return current;
    }
}
