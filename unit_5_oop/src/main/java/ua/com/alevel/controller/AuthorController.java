package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.impl.AuthorServiceImpl;

import static ua.com.alevel.BookLibrary.scan;

public class AuthorController {

    private final AuthorService authorService = new AuthorServiceImpl();

    public void runCrudAuthor(){
        boolean tmp = true;
        while(tmp){
            System.out.println("\tAuthor CRUD" + "\n" + "1. Create author" + "\n" +
                                "2. Update author by ID" + "\n" +
                                "3. Read all authors" + "\n" +
                                "4. Read author by ID" + "\n" +
                                "5. Delete author by ID" + "\n" +
                                "6. Return to the main menu" + "\n" + "Select one of the points: ");
            String position = scan.next();
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
    private void createAuthor(){
        Author author = new Author();
        System.out.print("Enter first name: ");
        author.setFirstName(scan.next());
        System.out.print("Enter last name: ");
        author.setLastName(scan.next());
        System.out.print("Enter amount of books: ");
        int N = scan.nextInt();
        for (int i = 0; i < N; i++) {
            System.out.print("Enter name book: ");
            String name = scan.next();
            author.setBooks(name);
        }
        authorService.create(author);
    }

    private void update(){
        System.out.print("Enter the ID of the author you want to update: ");
        Author author = authorService.findById(scan.nextInt());
        System.out.print("Enter NEW first name: ");
        author.setFirstName(scan.next());
        System.out.print("Enter NEW last name: ");
        author.setLastName(scan.next());
        System.out.print("Enter amount of books: ");
        int N = scan.nextInt();
        for (int i = 0; i < N; i++) {
            System.out.print("Enter name book: ");
            author.setBooks(scan.next());
        }
        authorService.update(author);
    }

    private void readAll(){
        System.out.println("Authors: " + authorService.findAll());
    }

    private void readById(){
        System.out.print("Enter the ID of the author you want to read: ");
        System.out.println("Author: " + authorService.findById(scan.nextInt()));
    }

    private void delete(){
        System.out.print("Enter the ID of the author you want to delete: ");
        authorService.delete(scan.nextInt());
    }
}
