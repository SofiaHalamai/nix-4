package ua.com.alevel.controller;

import ua.com.alevel.OrderedList;

import java.util.Arrays;
import java.util.List;

import static ua.com.alevel.Main.scan;

public class StringController {

    private List<String> listStr = new OrderedList<>();

    public void runStr(){
        boolean tmp = true;
        while(tmp){
            System.out.println("\tOrderedList by example of strings");
            MainController.menuForList();
            String position = scan.next();
            switch (position) {
                case "1":
                    addToList();
                    break;
                case "2":
                    deleteFromList();
                    break;
                case "3":
                    getObjectByIndex();
                    break;
                case "4":
                    System.out.println("Size of list: " + listStr.size());
                    break;
                case "5":
                    checkForContainsObject();
                    break;
                case "6":
                    listStr.clear();
                    System.out.println("List successfully cleared!");
                    break;
                case "7":
                    if (listStr.isEmpty())
                        System.out.println("List is empty!");
                    else
                        System.out.println("List isn't empty!");
                    break;
                case "8":
                    System.out.println(Arrays.toString(listStr.toArray()));
                    break;
                case "9":
                    tmp = false;
                    break;
                default:
                    System.out.println("Select one of the suggested points!");
                    break;
            }
        }
    }


    private void addToList (){
        try{
            System.out.println("Enter a value: ");
            String str = scan.next();
            listStr.add(str);
        } catch (Exception e){
            System.out.println("Incorrect input");
        }
    }

    private void deleteFromList (){
        try{
            System.out.println("Enter a value which you want to remove: ");
            String str = scan.next();
            if (listStr.remove(str)) {
                System.out.println("This value successfully remove from list!");
            }else
                System.out.println("No such element in list");
        } catch (Exception e){
            System.out.println("Incorrect input");
        }
    }

    private void getObjectByIndex(){
        try{
            System.out.println("Enter a index of object which you want to get: ");
            int num = scan.nextInt();
            System.out.println("Object of this index: " + listStr.get(num));
        } catch (Exception e){
            System.out.println("Incorrect input");
        }
    }

    private void checkForContainsObject(){
        try{
            System.out.println("Enter a object which you want check: ");
            String str = scan.next();
            if (listStr.contains(str)) {
                System.out.println("This object is in the list!");
            }else
                System.out.println("No such element in list");
        } catch (Exception e){
            System.out.println("Incorrect input");
        }
    }
}
