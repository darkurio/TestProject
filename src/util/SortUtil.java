package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import util.AlgorithmUtil.GridNode;

public class SortUtil {

    private static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] merge2(int left[], int right[]) {

        int[] merged = new int[left.length + right.length];

        int i = 0;
        int j = 0;
        int k = 0;

        // While left or right arrays have >= 1 element
        while (i < left.length || j < right.length) {
            // If both have >= 1 element
            if (i < left.length && j < right.length) {
                // Put min of the two into merged array and increment indices
                // accordingly
                if (left[i] < right[j]) {
                    merged[k++] = left[i++];
                } else {
                    merged[k++] = right[j++];
                }
            } else if (i < left.length) {
                // If only left array has elements, add all remaining in left to
                // merged array
                while (i < left.length) {
                    merged[k++] = left[i++];
                }
            } else {
                // If only right array has elements, add all remaining in right
                // to merged array
                while (j < right.length) {
                    merged[k++] = right[j++];
                }
            }
        }

        return merged;
    }

    public static int[] mergeSort2(int arr[]) {

        // Base Case
        if (arr.length <= 1) {
            return arr;
        }

        if (arr.length == 2) {
            if (arr[0] > arr[1]) {
                swap(arr, 0, 1);
            }
            return arr;
        }

        // Recursive Case

        int splitIndex = arr.length / 2;
        int left[] = new int[splitIndex];
        int right[] = new int[arr.length - splitIndex];
        int l = 0;
        int r = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i < splitIndex) {
                left[l++] = arr[i];
            } else {
                right[r++] = arr[i];
            }
        }

        left = mergeSort2(left);
        right = mergeSort2(right);

        return merge2(left, right);
    }

    private static void merge(int[] arr, int lo, int mid, int hi) {

        int[] merged = new int[hi - lo + 1];

        int i = lo;
        int j = mid + 1;
        int k = 0;

        // While both arrays have elements remaining, compare them
        while (i <= mid && j <= hi) {
            if (arr[i] < arr[j]) {
                merged[k++] = arr[i++];
            } else {
                merged[k++] = arr[j++];
            }
        }

        // Ony left array has elements remaining
        while (i <= mid) {
            merged[k++] = arr[i++];
        }

        // Only right array has elements remaining
        while (j <= hi) {
            merged[k++] = arr[j++];
        }

        // Copy results back into original array
        int x = lo;
        k = 0;
        while (k < merged.length) {
            arr[x++] = merged[k++];
        }
    }

    public static void mergeSort(int arr[], int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = (lo + hi) / 2;
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    // In each iteration, largest value 'bubbles' to top of array (one less
    // element to look at each time)
    // Leads to [unsorted | sorted]
    public static void bubbleSortAdaptive(int arr[]) {

        boolean swap = true;
        int x = 0;

        // For each element
        for (int i = 0; i < arr.length; i++) {

            // If no swaps were performed in this iteration, return
            // This means list is fully sorted already (for every pair of
            // elements, left ele <= right ele)
            if (!swap) {
                System.out.println("Comparisons: " + x);
                return;
            }
            swap = false;

            // Perform swaps on elements from start to array length - 1 - i
            // array length - 1 is because swap is done on curr and curr+1
            // indices
            // then -i is because we can ignore the right-most i elements
            // (supposedly containing the i largest elements in sorted order)
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swap = true;
                }
                x++;
            }
        }
    }

    public static void bubbleSort(int arr[]) {

        int x = 0;

        // For each element
        for (int i = 0; i < arr.length; i++) {
            // Perform swaps on elements from start to array length - 1 - i
            // array length - 1 is because swap is done on curr and curr+1
            // indices
            // then -i is because we can ignore the right-most i elements
            // (supposedly containing the i largest elements in sorted order)
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
                x++;
            }
        }
        System.out.println("Comparisons: " + x);
    }

    public static int bubbleSortRecursive(int a[], int end) {

        if (a.length <= 1 || end == 0) {
            return 0;
        }

        int x = 0;

        boolean swap = false;
        for (int i = 0; i < end; i++) {
            if (a[i] > a[i + 1]) {
                swap(a, i, i + 1);
                swap = true;
            }
            x++;
        }

        if (swap) {
            x += bubbleSortRecursive(a, end - 1);
        }

        return x;
    }

    /*
     * Looping through and SELECTING the min and swapping to first spot Ex. [sorted | unsorted] ->
     * [sorted | min of unsorted | unsorted] === [sorted | unsorted]
     */
    public static void selectionSort(int arr[]) {

        // Loop through all elements
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            // Loop through all elements to the right of the
            // current outer loop element and find the min element's index
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the min element with the current outer loop element
            swap(arr, i, minIndex);
        }
    }

    public static void selectionSortRecursive(int arr[], int start) {

        // Base Case
        if (start > arr.length - 1) {
            return;
        }

        // Recursive Case
        int minIndex = start;
        // Loop through all elements and find the min element's index
        for (int i = start; i < arr.length; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
        // Swap min element with first element
        swap(arr, start, minIndex);

        // Recurse over all elements to the right of the first element
        selectionSortRecursive(arr, start + 1);
    }

    /*
     * Looping through 'sorted' sublist and INSERTING into correct location Ex. [sorted | curr |
     * unsorted] and insert curr into sorted
     */
    public static void insertionSort(int arr[]) {

        // Loop through every element other than first element
        for (int i = 1; i < arr.length; i++) {
            // currIndex holds index of value being looked at in outer loop
            // As value being looked at moves, currIndex changes to keep
            // pointing to it
            int currIndex = i;

            // Note: Commented out segment is inefficient as it looks at ALL
            // elements in
            // sorted sublist even when it is unnecessary!
            // Loop through all elements before outer loop element
            /*
             * for (int j = i-1; j >= 0; j--) { // If outer loop value < current element... if
             * (arr[currIndex] < arr[j]) { swap(arr, currIndex, j); // Swap them and... currIndex =
             * j; // Update currIndex to keep pointing to outer loop value } }
             */

            // Loop through all elements before outer loop element
            // While we don't go past the first element in the sorted sublist
            // Keep swapping outer loop value until it hits a value <= it
            // At that point, the outer loop value is in the correct position in
            // the sorted sublist
            for (int j = i - 1; j >= 0 && arr[j] > arr[currIndex]; j--) {
                swap(arr, j, currIndex);
                currIndex = j; // Update currIndex to keep pointing to outer
                               // loop value
            }
        }
    }

    private static int partition2(int arr[], int left, int right) {
        int pivotIndex = left; // Always points to right-most value <= pivot
        for (int i = left + 1; i <= right; i++) { // Group all elements <= pivot value to the left
            if (arr[i] <= arr[left]) {
                swap(arr, i, ++pivotIndex); // Increment pivotIndex to index of left-most element >
                                            // pivot value
                                            // Swap left-most element > pivot value with element <=
                                            // pivot value
            }
        }
        swap(arr, left, pivotIndex); // Swap pivot into final position (right-most element <= pivot
                                     // value)
        return pivotIndex;
    }

    public static void quickSortInPlace2(int arr[], int left, int right) {

        // Base Case - if array has <= 1 element, is sorted already
        if (arr.length <= 1 || left >= right) {
            return;
        }

        // Recursive Case
        int pivotIndex = partition2(arr, left, right);

        // Sort on elements to the left and right of pivot
        quickSortInPlace2(arr, left, pivotIndex - 1);
        quickSortInPlace2(arr, pivotIndex + 1, right);
    }

    // Slight modification to implementation in CTCI
    // CTCI's does not necessarily place pivot in it's final location and hence
    // requires recursing on [left, pivot-1] and [pivot, right].
    // (As opposed to [pivot-1, right])
    private static int partition(int arr[], int left, int right) {

        int pivotValue = arr[left]; // Choose pivot as left-most value
        int i = left;
        int j = right;

        while (i < j) {
            // Increment i until we hit element > pivot value
            while (arr[i] <= pivotValue) {
                i++;
            }
            // Decrement j until we hit element <= pivot value
            while (arr[j] > pivotValue) {
                j--;
            }
            // i and j should always cross-over when they meet,
            // The meeting spot must either be <= or > pivot
            // which means either i or j would have gone 1 spot past it

            // If i and j haven't crossed over each other, swap elements at i and j and continue
            // This means element > pivot value swaps with element <= pivot value,
            // so elements > pivot value are grouped to the right and vice versa.
            if (i < j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        // j will be pointing to the right-most location <= pivot, so swap pivot into final location
        swap(arr, left, j);
        return j;
    }

    public static void quickSortInPlace(int arr[], int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(arr, left, right);
        quickSortInPlace(arr, left, pivot - 1);
        quickSortInPlace(arr, pivot + 1, right);
    }

    private static final class Node<T extends Comparable<T>> {

        private T value = null;
        private Node<T> prev = null;
        private Node<T> next = null;

        public Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    // Swap nodes in linked list by simply swapping their values
    public static final <T extends Comparable<T>> boolean swap(LinkedList<Node<T>> list, Node<T> a, Node<T> b) {

        if (a == null || b == null) {
            return false;
        }

        // Swap values
        T temp = a.value;
        a.value = b.value;
        b.value = temp;
        return true;
    }

    // Swap nodes in linked list by updating node pointers - this is the bad way to swap nodes
    // Side-effect is pointers to the swapped nodes in linked list will no longer point to the same
    // 'index' in the linked list, as the nodes themselves are swapped rather than the values held
    public static final <T extends Comparable<T>> boolean swapBad(LinkedList<Node<T>> list, Node<T> a, Node<T> b) {

        if (a == null || b == null) {
            return false;
        }

        // Swap a and b's prev/next's prev/next pointers
        if (a.prev != null) {
            a.prev.next = b;
        }
        if (a.next != null) {
            a.next.prev = b;
        }
        if (b.prev != null) {
            b.prev.next = a;
        }
        if (b.next != null) {
            b.prev.next = a;
        }

        // Swap a and b's prev/next pointers
        Node<T> temp = a.next;
        a.next = b.next;
        b.next = temp;
        temp = a.prev;
        a.prev = b.prev;
        b.prev = temp;

        return true;
    }

    public static final <T extends Comparable<T>> void insertionSortLL(LinkedList<Node<T>> list) {

        // If list has <= 1 elements, it is already sorted
        if (list.size() <= 1) {
            return;
        }

        // Partition linked list into [sorted | elementToSort | unsorted]
        Node<T> currUnsorted = list.get(1);
        while (currUnsorted != null) {

            // Push element to sort down into sorted partition until it falls into correct position
            Node<T> currSorted = currUnsorted.prev;
            Node<T> elementToSort = currUnsorted;
            while (elementToSort != null && elementToSort.value.compareTo(currSorted.value) < 0) {
                swap(list, currUnsorted, currSorted);
                elementToSort = elementToSort.prev;
            }

            // Increase sorted partition by 1 and move on to next unsorted element
            currUnsorted = currUnsorted.next;
        }
    }

    public static final int[] bucketSort(int[] a) {

        // Initialize buckets (array of linked lists)
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>(a.length);
        for (int i = 0; i < 10; i++) { // Assume need only 10 buckets, [0,9],[10,19],...
            buckets.add(new ArrayList<Integer>());
        }

        // Place elements into buckets based on some property (ex. tens place)
        for (int element : a) {
            buckets.get(element / 10).add(element);
        }

        // Sort each bucket
        for (int i = 0; i < buckets.size(); i++) {
            Collections.sort(buckets.get(i));
        }

        // Concatenate buckets into single result array and return it
        int j = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (int element : bucket) {
                a[j++] = element;
            }
        }

        return a;
    }

    public static int[] countingSort(int[] a) {

        // Compute range of input array
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int value : a) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }

        // Create histogram of input values
        int[] hist = new int[max - min + 1];
        for (int i = 0; i < hist.length; i++) {
            hist[i] = 0;
        }
        for (int value : a) {
            hist[value - min]++;
        }

        // Compute initial offsets for each value in output array and re-use histogram for storage
        int ptr = 0;
        for (int i = 0; i < hist.length; i++) {
            int temp = hist[i];
            hist[i] = ptr;
            ptr += temp;
        }

        // Place input values into output array
        int[] output = new int[a.length];
        for (int value : a) {
            output[hist[value - min]] = value;
            hist[value - min]++;
        }

        return output;
    }

    public static int[] lsdRadixSort(int[] a, int maxBit) {

        // For each bit in values (from bit 0 to maxBit)
        for (int i = 0; i <= maxBit; i++) {
            // Group elements based on bit i (stable)
            ArrayList<Integer> zeros = new ArrayList<Integer>();
            ArrayList<Integer> ones = new ArrayList<Integer>();
            for (int element : a) {
                if ((element & (1 << i)) == 0) {
                    zeros.add(element);
                } else {
                    ones.add(element);
                }
            }
            // Please grouped elements (stable) back into original array
            int j = 0;
            for (int zero : zeros) {
                a[j++] = zero;
            }
            for (int one : ones) {
                a[j++] = one;
            }
        }

        return a;
    }

    // NOTE: This code will not work b/c sorts being applied are not stable!
    public static int[] lsdRadixSortInPlace(int[] a, int maxBit) {

        // For each bit from 0 to maxBit
        for (int i = 0; i <= maxBit; i++) {

            int bit = 1 << i;
            // Partition array based on bit i (same logic as quicksort partition)
            // int m = 0, n = a.length - 1;
            // while (m < n) {
            // while (m < a.length && (a[m] & bit) == 0) {
            // m++;
            // }
            // while (n >= 0 && (a[n] & bit) == 1) {
            // n--;
            // }
            // if (m < n) {
            // swap(a, m, n);
            // m++;
            // n--;
            // }
            // }

            // Neither this nor the above commented code will work b/c they are not stable
            int leftMostOne = -1;
            for (int j = 0; j < a.length; j++) {
                if ((a[j] & bit) == 0 && leftMostOne >= 0) {
                    swap(a, j, leftMostOne++);
                } else if ((a[j] & bit) == 1 && leftMostOne < 0) {
                    leftMostOne = j;
                }
            }
        }

        return a;
    }

    public static int[] msdRadixSort(int[] a, int b, int left, int right) {

        // Base case
        if (b < 0 || left >= right) {
            return a;
        }

        // Group elements in array in [left, right] based on bit b
        ArrayList<Integer> zeros = new ArrayList<Integer>();
        ArrayList<Integer> ones = new ArrayList<Integer>();
        for (int i = left; i <= right; i++) {
            if ((a[i] & (1 << b)) == 0) {
                zeros.add(a[i]);
            } else {
                ones.add(a[i]);
            }
        }

        // Place grouped elements into original array
        int j = left;
        for (int zero : zeros) {
            a[j++] = zero;
        }
        for (int one : ones) {
            a[j++] = one;
        }

        // Recurse on element groups
        int leftGroupRightBound = left + zeros.size() - 1;
        msdRadixSort(a, b - 1, left, leftGroupRightBound);
        msdRadixSort(a, b - 1, leftGroupRightBound + 1, right);

        return a;
    }

    private static int partitionMsdRadixSort(int[] a, int lo, int hi, int b) {
        int left = lo, right = hi, bit = 1 << b;
        while (left < right) {
            while (left <= hi && (a[left] & bit) == 0) {
                left++;
            }
            while (right >= lo && (a[right] & bit) == 1) {
                right--;
            }
            if (left < right) {
                swap(a, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    // Note: Something not quite right in this method. Does not sort correctly.
    public static void msdRadixSortInPlace(int[] a, int lo, int hi, int b) {
        if (lo >= hi || b < 0) {
            return;
        }
        int left = SortUtil.partitionMsdRadixSort(a, lo, hi, b);
        // left will end up pointing to the left-most index with a 1 in bit b (if exists)
        msdRadixSortInPlace(a, lo, left - 1, b - 1);
        msdRadixSortInPlace(a, left, hi, b - 1);
    }
}
