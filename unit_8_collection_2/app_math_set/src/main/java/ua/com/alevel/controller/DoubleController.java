package ua.com.alevel.controller;

import ua.com.alevel.mathSet.Set;
import ua.com.alevel.mathSet.impl.MathSet;

import java.util.Arrays;

import static ua.com.alevel.Main.scan;

public class DoubleController {

    private final Set<Double> doubleSet = new MathSet<>();
    private int position;

    public void runDouble() {
        boolean tmp = true;
        while (tmp) {
            System.out.println("\tMathSet by example of Double");
            MainController.menuForSet();
            String position = scan.next();
            switch (position) {
                case "1":
                    addToSet();
                    break;
                case "2":
                    joinToSet();
                    break;
                case "3":
                    ascendingSort();
                    break;
                case "4":
                    descendingSort();
                    break;
                case "5":
                    getObjectByIndex();
                    break;
                case "6":
                    System.out.println("Max value: " + doubleSet.getMax());
                    break;
                case "7":
                    System.out.println("Min value: " + doubleSet.getMin());
                    break;
                case "8":
                    System.out.println("Average value: " + doubleSet.getAverage());
                    break;
                case "9":
                    System.out.println("Median of set: " + doubleSet.getMedian());
                    break;
                case "10":
                    squashSet();
                    break;
                case "11":
                    clearSet();
                    break;
                case "12":
                    System.out.println(Arrays.toString(doubleSet.toArray()));
                    break;
                case "13":
                    tmp = false;
                    break;
                default:
                    System.out.println("Select one of the suggested points!");
                    break;
            }
        }
    }

    private void addToSet() {
        try {
            System.out.println("If you want to add: just one value click - 1, or multiple values click - 0: ");
            position = scan.nextInt();
            if (position == 1) {
                System.out.print("Enter a value: ");
                Double num = scan.nextDouble();
                doubleSet.add(num);
            } else if (position == 0) {
                System.out.print("Enter a count of values which you want to add: ");
                position = scan.nextInt();
                Double[] doublArr = new Double[position];
                for (int i = 0; i < position; i++) {
                    System.out.print("Enter a value: ");
                    doublArr[i] = scan.nextDouble();
                }
                doubleSet.add(doublArr);
            } else
                System.out.println("Incorrect input");
        } catch (Exception e) {
            System.out.println("Incorrect input");
        }
    }

    private void joinToSet() {
        try {
            Set<Double> tmpForJoin = new MathSet<>();
            System.out.print("Enter a count of values which you want to join: ");
            position = scan.nextInt();
            for (int i = 0; i < position; i++) {
                System.out.print("Enter a value: ");
                tmpForJoin.add(scan.nextDouble());
            }
            doubleSet.join(tmpForJoin);
        } catch (Exception e) {
            System.out.println("Incorrect input");
        }
    }

    private void ascendingSort() {
        try {
            System.out.println("If you want to sort: all Set click - 0, or from index to index click - 1, or starting with a specific element click - 2: ");
            position = scan.nextInt();
            if (position == 0) {
                doubleSet.sortAsc();
                System.out.println("Sorted successfully");
            } else if (position == 1) {
                System.out.print("Enter a index for starting sort: ");
                int start = scan.nextInt();
                System.out.print("Enter a index for end of sort: ");
                int end = scan.nextInt();
                doubleSet.sortAsc(start, end);
                System.out.println("Sorted successfully");
            } else if (position == 2) {
                System.out.print("Enter a specific element for starting sort: ");
                doubleSet.sortAsc(scan.nextDouble());
                System.out.println("Sorted successfully");
            } else
                System.out.println("Incorrect input");
        } catch (Exception e) {
            System.out.println("Incorrect input");
        }
    }

    private void descendingSort() {
        try {
            System.out.println("If you want to sort: all Set click - 0, or from index to index click - 1, or starting with a specific element click - 2: ");
            position = scan.nextInt();
            if (position == 0) {
                doubleSet.sortDesc();
                System.out.println("Sorted successfully");
            } else if (position == 1) {
                System.out.print("Enter a index for starting sort: ");
                int start = scan.nextInt();
                System.out.print("Enter a index for end of sort: ");
                int end = scan.nextInt();
                doubleSet.sortDesc(start, end);
                System.out.println("Sorted successfully");
            } else if (position == 2) {
                System.out.print("Enter a specific element for starting sort: ");
                doubleSet.sortDesc(scan.nextDouble());
                System.out.println("Sorted successfully");
            } else
                System.out.println("Incorrect input");
        } catch (Exception e) {
            System.out.println("Incorrect input");
        }
    }

    private void getObjectByIndex() {
        try {
            System.out.println("Enter a index of number which you want to get: ");
            int num = scan.nextInt();
            System.out.println("Number of this index: " + doubleSet.get(num));
        } catch (Exception e) {
            System.out.println("Incorrect input");
        }
    }

    private void squashSet() {
        try {
            System.out.print("Enter a index for starting squash: ");
            int start = scan.nextInt();
            System.out.print("Enter a index for end of squash: ");
            int end = scan.nextInt();
            System.out.println("Your selected to squash set: " + Arrays.toString(doubleSet.squash(start, end).toArray()));
        } catch (Exception e) {
            System.out.println("Incorrect input");
        }
    }

    private void clearSet() {
        try {
            System.out.println("If you want to clear all set click - 1, if just part (array) - 0: ");
            position = scan.nextInt();
            if (position == 1) {
                doubleSet.clear();
                System.out.println("Set successfully cleared!");
            } else if (position == 0) {
                System.out.print("Enter a count of values which you want to clear: ");
                position = scan.nextInt();
                Double[] doublArr = new Double[position];
                for (int i = 0; i < position; i++) {
                    System.out.print("Enter a value: ");
                    doublArr[i] = scan.nextDouble();
                }
                doubleSet.clear(doublArr);
            } else
                System.out.println("Incorrect input");
        } catch (Exception e) {
            System.out.println("Incorrect input");
        }
    }
}
