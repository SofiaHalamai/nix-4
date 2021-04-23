package ua.com.alevel;

import ua.com.alevel.controller.CrudController;
import java.io.IOException;

public class BookStore {


    public static void main(String[] args) {
        CrudController controller = new CrudController();
        try {
            controller.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
