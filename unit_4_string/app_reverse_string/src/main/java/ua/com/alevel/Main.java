package ua.com.alevel;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputString, dest;
        int firstIndex, lastIndex;
        System.out.print("Enter a string for reverse: ");
        inputString = scan.nextLine();

        System.out.println("\n" + "Task 1: Do the reverse separately for each word");
        System.out.println("Result: " + ReverseString.reverse(inputString));

        System.out.println("\n" + "Task 2: Do the reverse in the specified word");
        System.out.print("Enter a word for reverse: ");
        dest = scan.nextLine();
        System.out.println("Result: " + ReverseString.reverse(inputString, dest));

        System.out.println("\n" + "Task 3: Do the reverse in the specified character interval");
        System.out.print("Enter a char number to start: ");
        firstIndex = scan.nextInt();
        System.out.print("Enter a char number to finish: ");
        lastIndex = scan.nextInt();
        System.out.println("Result: " + ReverseString.reverse(inputString, firstIndex,lastIndex));
    }
}
