package ua.com.alevel.controller;

import ua.com.alevel.mathSet.Set;
import ua.com.alevel.mathSet.impl.MathSet;

import java.util.Arrays;

import static ua.com.alevel.Main.scan;

public class IntegerController {

    private final Set<Integer> intSet = new MathSet<>();
    private int position;

    public void runInt() {
        boolean tmp = true;
        while (tmp) {
            System.out.println("\tMathSet by example of Integer");
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
                    System.out.println("Max value: " + intSet.getMax());
                    break;
                case "7":
                    System.out.println("Min value: " + intSet.getMin());
                    break;
                case "8":
                    System.out.println("Average value: " + intSet.getAverage());
                    break;
                case "9":
                    System.out.println("Median of set: " + intSet.getMedian());
                    break;
                case "10":
                    squashSet();
                    break;
                case "11":
                    clearSet();
                    break;
                case "12":
                    System.out.println(Arrays.toString(intSet.toArray()));
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
                Integer num = scan.nextInt();
                intSet.add(num);
            }
            else if (position == 0) {
                System.out.print("Enter a count of values which you want to add: ");
                position = scan.nextInt();
                Integer[] intArr = new Integer[position];
                for (int i = 0; i < position; i++) {
                    System.out.print("Enter a value: ");
                    intArr[i] = scan.nextInt();
                }
                intSet.add(intArr);
            } else
                System.out.println("Incorrect input");
        } catch (Exception e) {
            System.out.println("Incorrect input");
        }
    }

    private void joinToSet() {
        try {
            Set<Integer> tmpForJoin = new MathSet<>();
            System.out.print("Enter a count of values which you want to join: ");
            position = scan.nextInt();
            for (int i = 0; i < position; i++) {
                System.out.print("Enter a value: ");
                tmpForJoin.add(scan.nextInt());
            }
            intSet.join(tmpForJoin);
        } catch (
                Exception e) {
            System.out.println("Incorrect input");
        }
    }

    private void ascendingSort() {
        try {
            System.out.println("If you want to sort: all Set click - 0, or from index to index click - 1, or starting with a specific element click - 2: ");
            position = scan.nextInt();
            if (position == 0) {
                intSet.sortAsc();
                System.out.println("Sorted successfully");
            }
            else if (position == 1) {
                System.out.print("Enter a index for starting sort: ");
                int start = scan.nextInt();
                System.out.print("Enter a index for end of sort: ");
                int end = scan.nextInt();
                intSet.sortAsc(start, end);
                System.out.println("Sorted successfully");
            }
            else if (position == 2) {
                System.out.print("Enter a specific element for starting sort: ");
                position = scan.nextInt();
                intSet.sortAsc(position);
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
                intSet.sortDesc();
                System.out.println("Sorted successfully");
            }
            else if (position == 1) {
                System.out.print("Enter a index for starting sort: ");
                int start = scan.nextInt();
                System.out.print("Enter a index for end of sort: ");
                int end = scan.nextInt();
                intSet.sortDesc(start, end);
                System.out.println("Sorted successfully");
            }
            else if (position == 2) {
                System.out.print("Enter a specific element for starting sort: ");
                position = scan.nextInt();
                intSet.sortDesc(position);
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
            System.out.println("Number of this index: " + intSet.get(num));
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
            System.out.println("Your selected to squash set: " + Arrays.toString(intSet.squash(start, end).toArray()));
        } catch (Exception e) {
            System.out.println("Incorrect input");
        }
    }

    private void clearSet(){
        try {
            System.out.println("If you want to clear all set click - 1, if just part (array) - 0: ");
            position = scan.nextInt();
            if (position == 1) {
                intSet.clear();
                System.out.println("Set successfully cleared!");
            }
            else if (position == 0) {
                System.out.print("Enter a count of values which you want to clear: ");
                position = scan.nextInt();
                Integer[] intArr = new Integer[position];
                for (int i = 0; i < position; i++) {
                    System.out.print("Enter a value: ");
                    intArr[i] = scan.nextInt();
                }
                intSet.clear(intArr);
            } else
                System.out.println("Incorrect input");
        } catch (Exception e) {
            System.out.println("Incorrect input");
        }
    }
}

