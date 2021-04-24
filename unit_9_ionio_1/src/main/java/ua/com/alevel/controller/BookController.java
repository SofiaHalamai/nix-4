package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.impl.BookServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class BookController {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final BookService bookService = new BookServiceImpl();

    public void runCrudBook() throws IOException {
        boolean tmp = true;
        while (tmp) {
            System.out.println("\tBook CRUD" + "\n" + "1. Create book" + "\n" +
                    "2. Update book by ID" + "\n" +
                    "3. Read all books" + "\n" +
                    "4. Read book by ID" + "\n" +
                    "5. Find books by author" + "\n" +
                    "6. Delete book by ID" + "\n" +
                    "7. Return to the main menu" + "\n" + "Select one of the points: ");
            String position = reader.readLine();
            switch (position) {
                case "1":
                    createBook();
                    break;
                case "2":
                    update();
                    break;
                case "3":
                    readAll();
                    break;
                case "4":
                    readById();
                    break;
                case "5":
                    findBooks();
                    break;
                case "6":
                    delete();
                    break;
                case "7":
                    tmp = false;
                    break;
                default:
                    System.out.println("Select one of the suggested points!");
                    break;
            }
        }
    }

    private void createBook() {
        try {
            Book book = new Book();
            System.out.print("Enter name: ");
            book.setName(reader.readLine());
            System.out.println("Enter amount of book authors: ");
            int count = Integer.parseInt(reader.readLine());
            for (int i = 0; i < count; i++) {
                System.out.println("Enter author (first name and last name): ");
                String author = reader.readLine();
                book.setAuthors(author);
            }
            bookService.create(book);
        } catch (Exception e) {
            System.out.println("Incorrect input!");
        }
    }


    private void update() {
        try {
            System.out.print("Enter the ID of the book you want to update: ");
            int id = Integer.parseInt(reader.readLine());
            Book book = new Book();
            book.setId(id);
            System.out.print("Enter NEW name: ");
            book.setName(reader.readLine());
            System.out.println("Enter amount of book authors: ");
            int count = Integer.parseInt(reader.readLine());
            List<String> a = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                System.out.println("Enter author (first name and last name): ");
                String author = reader.readLine();
                a.add(author);
            }
            book.setAuthors(a);
            bookService.update(book);
        } catch (Exception e) {
            System.out.println("Incorrect input!");
        }
    }

    private void readAll() {
        System.out.println("Books: " + bookService.findAll());
    }

    private void readById() {
        try {
            System.out.print("Enter the ID of the book you want to read: ");
            int id = Integer.parseInt(reader.readLine());
            if (bookService.findById(id) == null)
                return;
            else
                System.out.println("Book: " + bookService.findById(id));
        } catch (Exception e) {
            System.out.println("Incorrect input!");
        }
    }

    private void delete() {
        try {
            System.out.print("Enter the ID of the book you want to delete: ");
            bookService.delete(Integer.parseInt(reader.readLine()));
        } catch (Exception e) {
            System.out.println("Incorrect input!");
        }
    }

    private void findBooks() throws IOException {
        Author author = new Author();
        System.out.print("Enter first name: ");
        author.setFirstName(reader.readLine());
        System.out.print("Enter last name: ");
        author.setLastName(reader.readLine());
        System.out.println("Books: " + bookService.findBooksByAuthor(author));
    }
}
