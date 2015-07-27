package util;

import java.util.ArrayList;

public class PrintUtil {

    public static void printArray(int arr[], int pivot, int left, int right) {
        System.out.println("Pivot=" + pivot + " Left=" + left + " Right=" + right);
        for (int i = 0; i < arr.length; i++) {
            if (i == left) {
                System.out.print("[");
            }
            if (i == pivot) {
                System.out.print("*");
            }
            System.out.print(arr[i]);
            if (i == right) {
                System.out.print("]");
            }
            if (i < arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }

    public static void printMatrix(String matrix[][], int size) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.print("[" + matrix[y][x] + "]");
            }
            System.out.println();
        }
    }

    public static void printMatrix(String matrix[][]) {
        int ySize = matrix.length;
        int xSize = ySize > 0 ? matrix[0].length : 0;
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                System.out.print("[" + matrix[y][x] + "]");
            }
            System.out.println();
        }
    }

    public static void printCharMatrix(char matrix[][]) {
        int ySize = matrix.length;
        int xSize = ySize > 0 ? matrix[0].length : 0;
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                System.out.print("[" + matrix[y][x] + "]");
            }
            System.out.println();
        }
    }

    public static void printIntMatrix(int matrix[][]) {
        for (int y = 0; y < matrix.length; y++) {
            int matrixYLength = matrix[y].length;
            System.out.print(y + "[" + matrixYLength + "]: ");
            for (int x = 0; x < matrixYLength; x++) {
                System.out.print("[" + matrix[y][x] + "]");
            }
            System.out.println();
        }
    }

    public static <T> void printArray(T arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }

    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }

    public static void printArrayList(ArrayList<Integer> a) {
        for (int i : a) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public static void printArrayListMatrix(ArrayList<ArrayList<Integer>> m) {
        for (ArrayList<Integer> row : m) {
            printArrayList(row);
        }
    }
}
