import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import util.AlgorithmUtil;
import util.AlgorithmUtil.BSTNode;
import util.AlgorithmUtil.GridNode;
import util.AlgorithmUtil.PathNode;
import util.AlgorithmUtil.PermData;
import util.PrintUtil;
import util.SearchUtil;
import util.SortUtil;
import concurrency.Consumer;
import concurrency.Producer;
import concurrency.Semaphore;
import concurrency.SemaphoreRunnable;
import concurrency.SharedResource;

public class MainDriver {

    public enum FIELD {
        BASE_COST, TAX_PERCENT, TAX_VALUE, TIP_PERCENT, TIP_VALUE, TOTAL_COST, NUM_PERSON, TOTAL_PER_PERSON
    };

    public static void main(String[] args) {

        int arr[] = { 57, 4, 5, 34, 20, 90, 78, 41, 34, 20 };
        // int sortedArr[] = { 4, 5, 20, 20, 34, 34, 41, 57, 78, 90 };
        int arrCopy[];
        //
        // System.out.println("Quicksort In-Place");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // SortUtil.quickSortInPlace(arrCopy, 0, arrCopy.length - 1);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("Quicksort In-Place 2");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // SortUtil.quickSortInPlace2(arrCopy, 0, arrCopy.length - 1);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("Insertion Sort");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // SortUtil.insertionSort(arrCopy);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("Selection Sort Recursive");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // SortUtil.selectionSortRecursive(arrCopy, 0);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("Selection Sort");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // SortUtil.selectionSort(arrCopy);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("Bubble Sort");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // SortUtil.bubbleSort(arrCopy);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("Bubble Sort Adaptive");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // SortUtil.bubbleSortAdaptive(arrCopy);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("Bubble Sort Recursive");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // System.out.println("Comparisons: "
        // + SortUtil.bubbleSortRecursive(arrCopy, arrCopy.length - 1));
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("Mergesort Array");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // arrCopy = SortUtil.mergeSort2(arrCopy);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("Mergesort Array (fewer array copies)");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // SortUtil.mergeSort(arrCopy, 0, arrCopy.length - 1);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("Bucketsort Array");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // arrCopy = SortUtil.bucketSort(arrCopy);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("Countingsort Array");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // arrCopy = SortUtil.countingSort(arrCopy);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("LSD Radixsort Array");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // arrCopy = SortUtil.lsdRadixSort(arrCopy, 7);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("LSD Radixsort In-Place Array");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // arrCopy = SortUtil.lsdRadixSortInPlace(arrCopy, 7);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("MSD Radixsort Array");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // arrCopy = SortUtil.msdRadixSort(arrCopy, 7, 0, arrCopy.length - 1);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("MSD Radixsort Array In-Place");
        // arrCopy = arr.clone();
        // PrintUtil.printArray(arrCopy);
        // SortUtil.msdRadixSortInPlace(arrCopy, 0, arrCopy.length - 1, 7);
        // PrintUtil.printArray(arrCopy);
        // System.out.println();
        //
        // System.out.println("Binary Search");
        // PrintUtil.printArray(arrCopy);
        // int val = 57;
        // System.out.println("Search for "
        // + val
        // + " loc="
        // + SearchUtil.binarySearchArray(arrCopy, val, 0,
        // arrCopy.length - 1));
        // System.out.println();
        //
        // System.out.println("Generate Matrix");
        // int size = 4;
        // String[][] m = AlgorithmUtil.generateMatrix(size);
        // PrintUtil.printMatrix(m, size);
        //
        // System.out.println("Rotate Matrix");
        // AlgorithmUtil.rotateMatrix(m, size);
        // PrintUtil.printMatrix(m, size);
        //

        // int x = 4;
        // int y = 5;
        // int[][] countPathsCache = new int[y + 1][x + 1];
        // for (int i = 0; i < y + 1; i++) {
        // for (int j = 0; j < x + 1; j++) {
        // countPathsCache[i][j] = -1;
        // }
        // }
        // System.out.println(AlgorithmUtil.countPaths(0, 0, x, y, countPathsCache));
        // System.out.println(AlgorithmUtil.countPathsBinomial(0, 0, x, y));

        // PathNode n3 = new PathNode(3, null, null);
        // PathNode n2 = new PathNode(2, n3, null);
        // PathNode n1 = new PathNode(1, null, n3, true);
        // PathNode n0 = new PathNode(0, n1, n2);
        // System.out.println(AlgorithmUtil.countPaths(n0, n3));

        // int[] arr1 = { 1, 2, 3, 4, 5 };
        // int[] arr2 = { 4, 5, 1, 2, 3 };
        // System.out.println(AlgorithmUtil.find(arr1, 0, arr.length - 1, 3));

        // char[] arr = { '0', '1', '2', '3' };
        // PrintUtil.printCharMatrix(AlgorithmUtil.getPermutations(arr));

        // String s = "012345";
        // PermData data = new AlgorithmUtil.PermData();
        // System.out.println("Expected: " + AlgorithmUtil.factorial(s.length()));
        // PrintUtil.printArrayList(AlgorithmUtil.getPermutations(s, data));
        // System.out.println(data.count);

        // String[][] matrix = new String[5][3];
        // for (int y = 0; y < matrix.length; y++) {
        // for (int x = 0; x < matrix[y].length; x++) {
        // matrix[y][x] = Integer.toString(y * matrix[y].length + x);
        // }
        // }
        // Node<String> head = AlgorithmUtil.convertToNodeMatrix(matrix, 0, 0);
        // System.out.println(AlgorithmUtil.countPathToTarget(head, "14"));

        // int[] coins = { 1, 3, 5 };
        // int total = 21;
        // PrintUtil.printArray(AlgorithmUtil.minCoins(total, coins));
        // PrintUtil.printIntMatrix(AlgorithmUtil.minCoins2(total, coins));
        // System.out.println(AlgorithmUtil.minCoins3(total, coins, new int[total + 1]));

        // System.out.println(AlgorithmUtil.numPplForSameBD(0.1));

        // int[] prices = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        // int[] cache = new int[prices.length];
        // int[] prev = new int[cache.length];
        // for (int i = 0; i < cache.length; i++) {
        // cache[i] = Integer.MIN_VALUE;
        // prev[i] = Integer.MIN_VALUE;
        // }
        // System.out.println("Top-down:");
        // for (int i = 0; i < prices.length; i++) {
        // System.out.println(i + ": $" + AlgorithmUtil.getMaxRevenueTopDown(prices, cache, prev,
        // i));
        // System.out.print("Cuts: ");
        // for (int n = i; n > 0; n -= prev[n]) {
        // System.out.print("[" + prev[n] + "]");
        // }
        // System.out.println();
        // }
        // for (int i = 0; i < cache.length; i++) {
        // cache[i] = Integer.MIN_VALUE;
        // prev[i] = Integer.MIN_VALUE;
        // }
        // System.out.println("Bottom-up:");
        // for (int i = 0; i < prices.length; i++) {
        // System.out.println(i + ": $" + AlgorithmUtil.getMaxRevenueBottomUp(prices, cache, prev,
        // i));
        // System.out.print("Cuts: ");
        // for (int n = i; n > 0; n -= prev[n]) {
        // System.out.print("[" + prev[n] + "]");
        // }
        // System.out.println();
        // }

        // System.out.println('a' > 'b');

        // Hanoi hanoi = new Hanoi(6);
        // hanoi.solve();

        // int numThreads = 3;
        // Thread[] consumerThreads = new Thread[numThreads];
        // Thread[] producerThreads = new Thread[numThreads];
        // SharedResource resource = new SharedResource();
        // for (int i = 0; i < numThreads; i++) {
        // producerThreads[i] = new Thread(new Producer(i, 10, resource));
        // consumerThreads[i] = new Thread(new Consumer(i, resource));
        // }
        // for (int i = 0; i < numThreads; i++) {
        // consumerThreads[i].start();
        // }
        // for (int i = 0; i < numThreads; i++) {
        // producerThreads[i].start();
        // }

        // int numThreads = 10;
        // Semaphore s = new Semaphore();
        // Thread[] threads = new Thread[numThreads];
        // for (int i = 0; i < numThreads; i++) {
        // threads[i] = new Thread(new SemaphoreRunnable(s, i));
        // }
        // for (int i = 0; i < numThreads; i++) {
        // threads[i].start();
        // }

        // int[] list = {1, 2, 3, 2, 3, 4, 5, 100, 20, 30, 40, 50, 60, 90};
        // ArrayList<Integer> longestContiguousSubsequence =
        // AlgorithmUtil.longestIncreasingContiguousSubsequence(list);
        // PrintUtil.printArrayList(longestContiguousSubsequence);
        // ArrayList<Integer> longestSubsequence = AlgorithmUtil.longestIncreasingSubsequence(list);
        // PrintUtil.printArrayList(longestSubsequence);

        // int[] list = {1, -3, 5, -2, 9, -8, -6, 4};
        // System.out.println(AlgorithmUtil.largestContiguousSum(list));

        // int totalChange = 50;
        // System.out.println(AlgorithmUtil.makeChange(totalChange, 25));
        // int [] choices = {1, 5, 10, 25};
        // System.out.println(AlgorithmUtil.makeChange2(totalChange, choices, 0));

        // System.out.println(AlgorithmUtil.lcm(51, 38));

        // Parent p = new Child();
        // p.instanceMethod();
        // p.staticMethod();
        // MyInterface myInterface = new InterfaceImplementation();
        // System.out.println(p.toString() + p.hashCode());
        // System.out.println(myInterface.outputInfo());

        // System.out.println(AlgorithmUtil.convertIntToExcelCode(1));
        // System.out.println(AlgorithmUtil.convertIntToExcelCode(53));
        // System.out.println(AlgorithmUtil.convertExcelCodeToInt("A"));
        // System.out.println(AlgorithmUtil.convertExcelCodeToInt("ABC"));
        // System.out.println(AlgorithmUtil.convertBinaryStringToInt("1100"));
        // System.out.println(AlgorithmUtil.convertIntToBinaryString(12));

        // int arr1[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        // System.out.println("Non-DP: " + AlgorithmUtil.max(arr1, 0, arr1.length - 1));
        // System.out.println("Non-DP count: " + AlgorithmUtil.maxCounter);
        // int cache[][] = new int[arr1.length][arr1.length];
        // for (int i = 0; i < cache.length; i++) {
        // for (int j = 0; j < cache[0].length; j++) {
        // cache[i][j] = -1;
        // }
        // }
        // System.out.println("DP: " + AlgorithmUtil.maxMemoize(arr1, 0, arr1.length - 1, cache));
        // System.out.println("DP count: " + AlgorithmUtil.maxCounterMemoize);

        // String s = "1224";
        // int cache[] = new int[s.length()];
        // System.out.println(AlgorithmUtil.countPossibleLetterStrings(s, 0, cache));

        // BSTNode<Integer> n1 = new BSTNode<Integer>(1);
        // BSTNode<Integer> n2 = new BSTNode<Integer>(2);
        // BSTNode<Integer> n3 = new BSTNode<Integer>(3);
        // BSTNode<Integer> n4 = new BSTNode<Integer>(4);
        // BSTNode<Integer> n5 = new BSTNode<Integer>(5);
        // BSTNode<Integer> n6 = new BSTNode<Integer>(6);
        // BSTNode<Integer> n7 = new BSTNode<Integer>(7);
        // n2.left = n1;
        // n2.right = n3;
        // n4.left = n2;
        // n4.right = n6;
        // n6.left = n5;
        // n6.right = n7;
        // AlgorithmUtil.traverseBSTIterativeInOrder(n4);

        // PrintUtil.printArray(arr);
        // AlgorithmUtil.reOrder(arr);
        // PrintUtil.printArray(arr);

        // int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        // AlgorithmUtil.rotateRight(a, 1);
        // PrintUtil.printArray(a);
        //
        // int n = 100;
        // ArrayList<ArrayList<Integer>> results = AlgorithmUtil.getFactors(n);
        // PrintUtil.printArrayListMatrix(results);
        // AlgorithmUtil.printFactors(n);
        //
        // ArrayList<Integer> primeFactors = AlgorithmUtil.getPrimeFactors(n);
        // System.out.println("V1:");
        // PrintUtil.printArrayList(primeFactors);
        // primeFactors = AlgorithmUtil.getPrimeFactors2(n);
        // System.out.println("V2:");
        // PrintUtil.printArrayList(primeFactors);
        //
        // System.out.println(AlgorithmUtil.pow(4, -3));

        // System.out.println(AlgorithmUtil.numEvenOddDigits(100));
    }
}
