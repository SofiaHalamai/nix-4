package ua.com.alevel.controller;

import static ua.com.alevel.BookLibrary.scan;

public class CrudController {

    public void run(){
        while(true){
            System.out.println("\tCRUD" + "\n" + "1. Book CRUD" + "\n" +
                    "2. Author CRUD" + "\n" +
                    "3. Exit" + "\n" + "Select one of the points: ");
            String position = scan.next();
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
