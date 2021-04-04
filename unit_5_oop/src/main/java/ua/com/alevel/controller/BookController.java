package ua.com.alevel.controller;

import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.impl.BookServiceImpl;

import static ua.com.alevel.BookLibrary.scan;

public class BookController {

    private final BookService bookService = new BookServiceImpl();

    public void runCrudBook(){
        boolean tmp = true;
        while(tmp){
            System.out.println("\tBook CRUD" + "\n" + "1. Create book" + "\n" +
                    "2. Update book by ID" + "\n" +
                    "3. Read all books" + "\n" +
                    "4. Read book by ID" + "\n" +
                    "5. Delete book by ID" + "\n" +
                    "6. Return to the main menu" + "\n" + "Select one of the points: ");
            String position = scan.next();
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
                    delete();
                    break;
                case "6":
                    tmp = false;
                    break;
                default:
                    System.out.println("Select one of the suggested points!");
                    break;
            }
        }
    }
    private void createBook(){
        Book book = new Book();
        System.out.print("Enter name: ");
        book.setName(scan.next());
        System.out.println("Enter amount of book authors: ");
        int count = scan.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.println("Enter authors: ");
            String author = scan.next();
            book.setAuthors(author);
        }
        bookService.create(book);
    }


    private void update(){
        System.out.print("Enter the ID of the book you want to update: ");
        Book book = bookService.findById(scan.nextInt());
        System.out.print("Enter NEW name: ");
        book.setName(scan.next());
        System.out.println("Enter amount of book authors: ");
        int count = scan.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.println("Enter authors: ");
            String author = scan.next();
            book.setAuthors(author);
        }
        bookService.update(book);
    }

    private void readAll(){
        System.out.println("Books: " + bookService.findAll());
    }

    private void readById(){
        System.out.print("Enter the ID of the book you want to read: ");
        System.out.println("Book: " + bookService.findById(scan.nextInt()));
    }

    private void delete(){
        System.out.print("Enter the ID of the book you want to delete: ");
        bookService.delete(scan.nextInt());
    }
}
