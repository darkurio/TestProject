package util;

public class SearchUtil {
    /*
     * Note: calculation of midIndex When num elements is even: right + left
     * chooses left of middle right + left + 1 chooses right of middle When num
     * elements is odd, both methods choose middle element
     */
    public static int binarySearchArray(int arr[], int val, int left, int right) {

        // Base Case
        if (left > right) {
            return -1; // when element is not in array
        }

        // Recursive Case
        int midIndex = (left + right) / 2;
        if (arr[midIndex] == val) {
            return midIndex;
        } else if (val < arr[midIndex]) {
            return binarySearchArray(arr, val, left, midIndex - 1);
        } else {
            return binarySearchArray(arr, val, midIndex + 1, right);
        }
    }
}
