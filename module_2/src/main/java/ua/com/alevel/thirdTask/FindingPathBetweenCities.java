package ua.com.alevel.thirdTask;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static ua.com.alevel.controller.MainController.*;

public class FindingPathBetweenCities {
    private static int N;

    public static void workWithFileCities(String inputPath, String outputPath) throws IOException {
        List<String> cities = new ArrayList<>();
        int result;
        BufferedReader reader = new BufferedReader(new FileReader(inputPath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
        System.out.print(ANSI_BLUE + "READING DATA FROM FILE ... COUNT OF CITIES:  " + ANSI_RESET);
        N = Integer.parseInt(reader.readLine());
        System.out.println(N);
        int[][] linkMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            cities.add(reader.readLine());
            int countNeighbors = Integer.parseInt(reader.readLine());
            for (int j = 0; j < countNeighbors; j++) {
                StringTokenizer indexNeighborAndCost = new StringTokenizer(reader.readLine(), " ");
                linkMatrix[i][Integer.parseInt(indexNeighborAndCost.nextToken()) - 1] = Integer.parseInt(indexNeighborAndCost.nextToken());
            }
        }
        System.out.println(ANSI_BLUE + "LIST OF CITIES:  " + ANSI_RESET + cities);
        System.out.println(ANSI_BLUE + "ADJACENCY MATRIX:  " + ANSI_RESET);
        print(linkMatrix);

        int numberPathsToFind = Integer.parseInt(reader.readLine());
        StringBuilder paths = new StringBuilder();
        for (int i = 0; i < numberPathsToFind; i++)
            paths.append(reader.readLine()).append(",");

        StringTokenizer path = new StringTokenizer(paths.toString(), ",");
        for (int j = 0; j < numberPathsToFind; j++) {
            StringTokenizer fromTo = new StringTokenizer(path.nextToken(), " ");
            String fromCity = fromTo.nextToken();
            System.out.print(ANSI_BLUE + "FROM CITY -> " + ANSI_RESET + fromCity);
            int indexFromCity = 0;
            String toCity = fromTo.nextToken();
            System.out.println(ANSI_BLUE + " TO CITY -> " + ANSI_RESET + toCity);
            int indexToCity = 0;
            for (int i = 0; i < cities.size(); i++)
                if (cities.get(i).equals(fromCity)) indexFromCity = i;
            for (int i = 0; i < cities.size(); i++)
                if (cities.get(i).equals(toCity)) indexToCity = i;
                if (indexFromCity < indexToCity) {
                    result = FindingPathBetweenCities.findMinPath(indexFromCity, indexToCity, linkMatrix);
                }else
                    result = FindingPathBetweenCities.findMinPath(indexToCity, indexFromCity, linkMatrix);
            System.out.println(ANSI_BLUE + "SHORTEST WAY = " + ANSI_RESET + result);
            writer.append(String.valueOf(result)).append("\n");
        }
        reader.close();
        writer.close();
    }

    private static boolean checkForContinueCount(int[][] weight, int start, int end) {
        for (int i = start; i < end + 1; i++) {
            for (int j = start; j < end + 1; j++) {
                if (weight[i][j] < 0)
                    return true;
            }
        }
        return false;
    }

    private static void print(int[][] matrix) {
        for (int[] element : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(element[j] + "    ");
            }
            System.out.println();
        }
    }

    public static int findMinPath(int fromCity, int toCity, int[][] linkMatrix) {
        int maxPath = 200_000;
        int[][] matrixOfWeight = new int[N][N];
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
