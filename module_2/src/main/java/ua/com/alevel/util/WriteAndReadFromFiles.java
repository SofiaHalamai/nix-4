package ua.com.alevel.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static ua.com.alevel.controller.MainController.ANSI_GREEN;
import static ua.com.alevel.controller.MainController.ANSI_RESET;

public class WriteAndReadFromFiles {

    public static List<String> readFromFile (String pathName){
        List <String> list = new ArrayList<>();
        File file = new File(pathName);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(ANSI_GREEN + "INVALID FILE PATH" + ANSI_RESET);
        }
        return list;
    }

    public static void writeToFile (List<String> list, String pathName){
        try {
        FileWriter writer = new FileWriter(pathName);
        for(String l : list){
            writer.write(l + "\n");
        }
        writer.close();
        } catch (IOException e) {
            System.out.println(ANSI_GREEN + "INVALID FILE PATH" + ANSI_RESET);
        }
    }
}
