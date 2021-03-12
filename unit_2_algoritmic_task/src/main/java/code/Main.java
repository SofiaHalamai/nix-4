package code;

import code.tasks.CountingTheEndTimeOfLesson;
import code.tasks.NumberOfCharactersInString;
import code.tasks.SumOfNumberInString;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputString;
        int numberOfSomething;

        System.out.println("Task 1: in the entered string, isolate all the numbers and find their sum");
        System.out.print("Enter a string: ");
        inputString = sc.nextLine();
        SumOfNumberInString sumOfNumberInString = new SumOfNumberInString(inputString);
        System.out.println("Sum of number in the entered string: " + sumOfNumberInString.findingSum());

        System.out.println("\n" + "Task 2: in the entered string, extracts all Latin / Cyrillic characters and sorts them, indicating the number of occurrences of each character");
        System.out.print("Enter a string: ");
        inputString = sc.nextLine();
        NumberOfCharactersInString numberOfCharactersInString = new NumberOfCharactersInString(inputString);
        numberOfCharactersInString.countChar();

        System.out.println("\n" + "Task 3: determine when the lesson ends");
        System.out.print("Enter a lesson (1 - 10): ");
        numberOfSomething = sc.nextInt();
        CountingTheEndTimeOfLesson countingTheEndTimeOfLesson = new CountingTheEndTimeOfLesson(numberOfSomething);
        countingTheEndTimeOfLesson.calculationOfTime();
    }
}
