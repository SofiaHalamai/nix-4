package ua.com.alevel.firstLevel.numberUniqueSymbols;

import static ua.com.alevel.Main.scan;

public class ArrayDataProcessing {

    public void searchAllUniqueSymbols(){
        int [] arr = dataOfArray();
        NumberUniqueSymbols numberUniqueSymbols = new NumberUniqueSymbols();
        System.out.println("Number unique symbols in array: " + numberUniqueSymbols.uniqueCount(arr));
    }

    private static int[] dataOfArray(){
        System.out.print("Enter a size of array: ");
        int N = scan.nextInt();
        if (!checkCorrectSize(N)) {
            System.out.println("Uncorrected entered size of array. Try again: ");
            N = scan.nextInt();
        }
        int [] array = new int[N];
        array = fillArray(array);
        System.out.print("The resulting array: ");
        printArray(array);
        return array;
    }

    private static int[] fillArray(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.println("Enter element " + "[" + (i) + "]: ");
            arr[i] = scan.nextInt();
        }
        return arr;
    }

    private static void printArray(int [] arr){
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    private static boolean checkCorrectSize(int size){
        if (size <=0) return false;
        else
            return true;
    }
}
