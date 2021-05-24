package ua.com.alevel.util;

public class FindingPathBetweenCities {

    private static boolean checkForContinueCount(int[][] weight, int start, int end) {
        for (int i = start; i < end + 1; i++) {
            for (int j = start; j < end + 1; j++) {
                if (weight[i][j] < 0)
                    return true;
            }
        }
        return false;
    }

    public static int findMinPath(int fromCity, int toCity, int[][] linkMatrix) {
        int maxPath = 200_000;
        int[][] matrixOfWeight = new int[linkMatrix.length][linkMatrix.length];
        for (int i = 0; i < toCity + 1; i++) {
            for (int j = 0; j < toCity + 1; j++) {
                matrixOfWeight[i][j]= -1;
                if (i >= fromCity && j == fromCity) matrixOfWeight[i][j] = 0;
                if (i == fromCity && j > fromCity) matrixOfWeight[i][j] = maxPath;
            }
        }
        int startValue = matrixOfWeight[fromCity][fromCity];
        int countI = fromCity + 1;
        int countJ = fromCity + 1;
        do {
            int min = 0;
            int minI = 0;
            int minJ = 0;
            for (int j = countJ; j < toCity + 1; j++) {
                if (matrixOfWeight[countI][j] <= 0) {
                    if (linkMatrix[countI - 1][j] != 0) {
                        matrixOfWeight[countI][j] = linkMatrix[countI - 1][j] + startValue;
                    } else {
                        matrixOfWeight[countI][j] = maxPath;
                    }
                    if (j == countJ || min > matrixOfWeight[countI][j]) {
                        min = matrixOfWeight[countI][j];
                        minI = countI;
                        minJ = j;
                    }
                    if (j != 1 && matrixOfWeight[countI-1][j] < matrixOfWeight[countI][j]){
                        matrixOfWeight[countI][j] = matrixOfWeight[countI-1][j];
                        min = matrixOfWeight[countI-1][j];
                        minI = countI-1;
                    }
                }
            }
            startValue = min;
            for (int a = minI + 1; a < toCity + 1; a++) {
                matrixOfWeight[a][minJ] = min;
            }
            countI++;
            countJ++;
        } while (checkForContinueCount(matrixOfWeight, fromCity, toCity));
        return matrixOfWeight[toCity][toCity];
    }
}
