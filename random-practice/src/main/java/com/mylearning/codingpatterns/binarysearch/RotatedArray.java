package com.mylearning.codingpatterns.binarysearch;

public class RotatedArray {

    /*The problem follows the Binary Search pattern. We can use a similar approach as discussed in Order-agnostic Binary Search and modify
    it similar to Search Bitonic Array to search for the ‘key’ in the rotated array.

    After calculating the middle, we can compare the numbers at indices start and middle. This will give us two options:

    If arr[start] <= arr[middle], the numbers from start to middle are sorted in ascending order.
    Else, the numbers from middle+1 to end are sorted in ascending order.

    Once we know which part of the array is sorted, it is easy to adjust our ranges. For example, if option-1 is true, we have two choices:

    By comparing the ‘key’ with the numbers at index start and middle we can easily find out if the ‘key’ lies between indices start and middle;
    if it does, we can skip the second part => end = middle -1. Else, we can skip the first part => start = middle + 1.
     */
    public static int search(int[] arr, int key) {
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == key)
                return mid;

            if (arr[start] <= arr[mid]) { // left side is sorted in ascending order
                if (key >= arr[start] && key < arr[mid]) {
                    end = mid - 1;
                } else { //key > arr[mid]
                    start = mid + 1;
                }
            } else { // right side is sorted in ascending order
                if (key > arr[mid] && key <= arr[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        // we are not able to find the element in the given array
        return -1;
    }

    /*In this problem, actually, we are asked to find the index of the minimum element. The number of times the minimum element is moved
    to the right will be equal to the number of rotations. An interesting fact about the minimum element is that it is the only element
    in the given array which is smaller than its previous element. Since the array is sorted in ascending order, all other elements are
    bigger than their previous element.

    After calculating the middle, we can compare the number at index middle with its previous and next number. This will give us two options:

    If arr[middle] > arr[middle + 1], then the element at middle + 1 is the smallest.
    If arr[middle - 1] > arr[middle], then the element at middle is the smallest.

    To adjust the ranges we can follow the same approach as discussed in Search in Rotated Array. Comparing the numbers at indices start
    and middle will give us two options:

    If arr[start] < arr[middle], the numbers from start to middle are sorted.
    Else, the numbers from middle + 1 to end are sorted.*/
    public static int countRotations(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;

            if (mid < end && arr[mid] > arr[mid + 1]) // if mid is greater than the next element
                return mid + 1;
            if (mid > start && arr[mid - 1] > arr[mid]) // if mid is smaller than the previous element
                return mid;

            if (arr[start] < arr[mid]) { // left side is sorted, so the pivot is on right side
                start = mid + 1;
            } else { // right side is sorted, so the pivot is on the left side
                end = mid - 1;
            }
        }

        return 0; // the array has not been rotated
    }

    /*
    We can follow the same approach as discussed in Search in Rotated Array. The only difference is that before incrementing start or decrementing end,
    we will check if either of them is the smallest number.
    */
    public static int countRotationsWithDuplicates(int[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException();

        int start = 0, end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid < end && arr[mid] > arr[mid + 1]) // if element at mid is greater than the next element
                return mid + 1;
            if (mid > start && arr[mid - 1] > arr[mid]) // if element at mid is smaller than the previous element
                return mid;

            // this is the only difference from the previous solution
            // if numbers at indices start, mid, and end are same, we can't choose a side
            // the best we can do is to skip one number from both ends if they are not the smallest number
            if (arr[start] == arr[mid] && arr[end] == arr[mid]) {
                if (arr[start] > arr[start + 1]) // if element at start+1 is not the smallest
                    return start + 1;
                ++start;
                if (arr[end - 1] > arr[end]) // if the element at end is not the smallest
                    return end;
                --end;
                // left side is sorted, so the pivot is on right side
            } else if (arr[start] < arr[mid] || (arr[start] == arr[mid] && arr[mid] > arr[end])) {
                start = mid + 1;
            } else { // right side is sorted, so the pivot is on the left side
                end = mid - 1;
            }
        }

        return 0; // the array has not been rotated
    }


    public static void main(String[] args) {
        System.out.println(RotatedArray.search(new int[] { 10, 15, 1, 3, 8 }, 15));
        System.out.println(RotatedArray.search(new int[] { 4, 5, 7, 9, 10, -1, 2 }, 10));

        System.out.println(RotatedArray.countRotations(new int[] { 10, 15, 1, 3, 8 }));
        System.out.println(RotatedArray.countRotations(new int[] { 4, 5, 7, 9, 10, -1, 2 }));
        System.out.println(RotatedArray.countRotations(new int[] { 1, 3, 8, 10 }));

        System.out.println(RotatedArray.countRotationsWithDuplicates(new int[] { 3, 3, 7, 3 }));
    }
}
