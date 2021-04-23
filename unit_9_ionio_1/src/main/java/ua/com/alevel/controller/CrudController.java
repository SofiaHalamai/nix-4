package ua.com.alevel.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CrudController {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void run() throws IOException {
        while(true){
            System.out.println("\tCRUD" + "\n" + "1. Book CRUD" + "\n" +
                    "2. Author CRUD" + "\n" +
                    "3. Exit" + "\n" + "Select one of the points: ");
            String position = reader.readLine();
            switch (position) {
                case "1":
                    BookController bookController = new BookController();
                    bookController.runCrudBook();
                    break;
                case "2":
                    AuthorController authorController = new AuthorController();
                    authorController.runCrudAuthor();
                    break;
                case "3":
                    System.exit(1);
                default:
                    System.out.println("Select one of the suggested points!");
                    break;
            }
        }
    }
}
