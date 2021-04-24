package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.impl.AuthorServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class AuthorController {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final AuthorService authorService = new AuthorServiceImpl();

    public void runCrudAuthor() throws IOException {
        boolean tmp = true;
        while (tmp) {
            System.out.println("\tAuthor CRUD" + "\n" + "1. Create author" + "\n" +
                    "2. Update author by ID" + "\n" +
                    "3. Read all authors" + "\n" +
                    "4. Read author by ID" + "\n" +
                    "5. Find authors by book" + "\n" +
                    "6. Delete author by ID" + "\n" +
                    "7. Return to the main menu" + "\n" + "Select one of the points: ");
            String position = reader.readLine();
            switch (position) {
                case "1":
                    createAuthor();
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
                    findAuthors();
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

    private void createAuthor() {
        try {
            Author author = new Author();
            System.out.print("Enter first name: ");
            author.setFirstName(reader.readLine());
            System.out.print("Enter last name: ");
            author.setLastName(reader.readLine());
            System.out.print("Enter amount of books: ");
            int N = Integer.parseInt(reader.readLine());
            for (int i = 0; i < N; i++) {
                System.out.print("Enter name book: ");
                String name = reader.readLine();
                author.setBooks(name);
            }
            authorService.create(author);
        } catch (Exception e) {
            System.out.println("Incorrect input!");
        }

    }

    private void update() {
        try {
            System.out.print("Enter the ID of the author you want to update: ");
            int id = Integer.parseInt(reader.readLine());
            Author author = new Author();
            author.setId(id);
            System.out.print("Enter NEW first name: ");
            author.setFirstName(reader.readLine());
            System.out.print("Enter NEW last name: ");
            author.setLastName(reader.readLine());
            System.out.print("Enter amount of books: ");
            int N = Integer.parseInt(reader.readLine());
            List<String> b = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                System.out.print("Enter name book: ");
                String nameBook = reader.readLine();
                b.add(nameBook);
            }
            author.setBooks(b);
            authorService.update(author);
        } catch (Exception e) {
            System.out.println("Incorrect input!");
        }
    }

    private void readAll() {
        System.out.println("Authors: " + authorService.findAll());
    }

    private void readById() {
        try {
            System.out.print("Enter the ID of the author you want to read: ");
            int id = Integer.parseInt(reader.readLine());
            if (authorService.findById(id) == null)
                return;
            else
                System.out.println("Author: " + authorService.findById(id));
        } catch (Exception e) {
            System.out.println("Incorrect input!");
        }
    }

    private void delete() {
        try {
            System.out.print("Enter the ID of the author you want to delete: ");
            authorService.delete(Integer.parseInt(reader.readLine()));
        } catch (Exception e) {
            System.out.println("Incorrect input!");
        }
    }

    private void findAuthors() throws IOException {
        Book book = new Book();
        System.out.print("Enter name: ");
        book.setName(reader.readLine());
        System.out.println("Authors: " + authorService.findAuthorsByBook(book));
    }
}
