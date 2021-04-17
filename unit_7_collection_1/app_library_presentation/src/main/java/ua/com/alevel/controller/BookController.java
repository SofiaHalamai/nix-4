package ua.com.alevel.controller;

import ua.com.alevel.OrderedList;
import ua.com.alevel.entity.Book;

import java.util.Arrays;
import java.util.List;

import static ua.com.alevel.Main.scan;

public class BookController {

    private List<Book> listBook = new OrderedList<>();


    public void runBook(){
        boolean tmp = true;
        while(tmp){
            System.out.println("\tOrderedList by example of Books");
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
                    getBookByIndex();
                    break;
                case "4":
                    System.out.println("Size of list: " + listBook.size());
                    break;
                case "5":
                    checkForContainsBook();
                    break;
                case "6":
                    listBook.clear();
                    System.out.println("List successfully cleared!");
                    break;
                case "7":
                    if (listBook.isEmpty())
                        System.out.println("List is empty!");
                    else
                        System.out.println("List isn't empty!");
                    break;
                case "8":
                    System.out.println(Arrays.toString(listBook.toArray()));
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
            Book book = new Book();
            System.out.println("Enter a name of book: ");
            book.setName(scan.next());
            System.out.println("Enter a pages in the book: ");
            book.setPages(scan.nextInt());
            listBook.add(book);
        } catch (Exception e){
            System.out.println("Incorrect input");
        }
    }

    private void deleteFromList (){
        try{
            Book book = new Book();
            System.out.println("Enter a name of book which you want to remove: ");
            book.setName(scan.next());
            System.out.println("Enter a pages in the book which you want to remove: ");
            book.setPages(scan.nextInt());
            if (listBook.remove(book)) {
                System.out.println("This book successfully remove from list!");
            }else
                System.out.println("No such book in list");
        } catch (Exception e){
            System.out.println("Incorrect input");
        }
    }

    private void getBookByIndex(){
        try{
            System.out.println("Enter a index of book which you want to get: ");
            int num = scan.nextInt();
            System.out.println("Book of this index: " + listBook.get(num));
        } catch (Exception e){
            System.out.println("Incorrect input");
        }
    }

    private void checkForContainsBook(){
        try{
            Book book = new Book();
            System.out.println("Enter a name of book which you want to check: ");
            book.setName(scan.next());
            System.out.println("Enter a pages in the book which you want to cheak: ");
            book.setPages(scan.nextInt());
            if (listBook.contains(book)) {
                System.out.println("This book is in the list!");
            }else
                System.out.println("No such book in list");
        } catch (Exception e){
            System.out.println("Incorrect input");
        }
    }
}
