package com.mylearning.codingpatterns.binarysearch;

public class CeilingFloorNextMinimumDiff {

    public static int orderAgnosticSearch(int[] arr, int key) {
        int start = 0, end = arr.length - 1;
        boolean isAscending = arr[start] < arr[end];
        while (start <= end) {
            // calculate the middle of the current range
            int mid = start + (end - start) / 2;

            if (key == arr[mid])
                return mid;

            if (isAscending) { // ascending order
                if (key < arr[mid]) {
                    end = mid - 1; // the 'key' can be in the first half
                } else { // key > arr[mid]
                    start = mid + 1; // the 'key' can be in the second half
                }
            } else { // descending order
                if (key > arr[mid]) {
                    end = mid - 1; // the 'key' can be in the first half
                } else { // key < arr[mid]
                    start = mid + 1; // the 'key' can be in the second half
                }
            }
        }
        return -1; // element not found
    }

    public static int searchCeilingOfANumber(int[] arr, int key) {
        if (key > arr[arr.length - 1]) // if the 'key' is bigger than the biggest element
            return -1;

        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < arr[mid]) {
                end = mid - 1;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else { // found the key
                return mid;
            }
        }
        // since the loop is running until 'start <= end', so at the end of the while loop, 'start == end+1'
        // we are not able to find the element in the given array, so the next big number will be arr[start]
        return start;
    }

    public static int searchFloorOfANumber(int[] arr, int key) {
        if (key < arr[0]) // if the 'key' is smaller than the smallest element
            return -1;

        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < arr[mid]) {
                end = mid - 1;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else { // found the key
                return mid;
            }
        }
        // since the loop is running until 'start <= end', so at the end of the while loop, 'start == end+1'
        // we are not able to find the element in the given array, so the next smaller number will be arr[end]
        return end;
    }

    /*
    Given an array of lowercase letters sorted in ascending order, find the smallest letter in the given array greater than a given ‘key’.
    Assume the given array is a circular list, which means that the last letter is assumed to be connected with the first letter.
    This also means that the smallest letter in the given array is greater than the last letter of the array and is also the first letter of the array.
    Input: ['a', 'c', 'f', 'h'], key = 'h'
    Output: 'a'
    */
    public static char searchNextLetter(char[] letters, char key) {
        int n = letters.length;
        if (key < letters[0] || key > letters[n - 1])
            return letters[0];

        int start = 0, end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < letters[mid]) {
                end = mid - 1;
            } else { //if (key >= letters[mid]) {
                start = mid + 1;
            }
        }
        // since the loop is running until 'start <= end', so at the end of the while loop, 'start == end+1'
        return letters[start % n];
    }

    /*Given an array of numbers sorted in ascending order, find the element in the array that has the minimum difference with the given ‘key’.
    We can use a similar approach as discussed in Order-agnostic Binary Search. We will try to search for the ‘key’ in the given array.
    If we find the ‘key’ we will return it as the minimum difference number. If we can’t find the ‘key’, (at the end of the loop) we can find
    the differences between the ‘key’ and the numbers pointed out by indexes start and end, as these two numbers will be closest to the ‘key’.
    The number that gives minimum difference will be our required number.*/
    public static int searchMinDiffElement(int[] arr, int key) {
        if (key < arr[0])
            return arr[0];
        if (key > arr[arr.length - 1])
            return arr[arr.length - 1];

        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < arr[mid]) {
                end = mid - 1;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else {
                return arr[mid];
            }
        }

        // at the end of the while loop, 'start == end+1'
        // we are not able to find the element in the given array
        // return the element which is closest to the 'key'
        if ((arr[start] - key) < (key - arr[end]))
            return arr[start];
        return arr[end];
    }

    public static void main(String[] args) {

        System.out.println(orderAgnosticSearch(new int[] { 4, 6, 10 }, 10));
        System.out.println(orderAgnosticSearch(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 5));
        System.out.println(orderAgnosticSearch(new int[] { 10, 6, 4 }, 10));
        System.out.println(orderAgnosticSearch(new int[] { 10, 6, 4 }, 4));
        
        System.out.println(searchCeilingOfANumber(new int[] { 4, 6, 10 }, 6));
        System.out.println(searchCeilingOfANumber(new int[] { 1, 3, 8, 10, 15 }, 12));
        System.out.println(searchCeilingOfANumber(new int[] { 4, 6, 10 }, 17));
        System.out.println(searchCeilingOfANumber(new int[] { 4, 6, 10 }, -1));

        System.out.println(searchFloorOfANumber(new int[] { 4, 6, 10 }, 6));
        System.out.println(searchFloorOfANumber(new int[] { 1, 3, 8, 10, 15 }, 12));
        System.out.println(searchFloorOfANumber(new int[] { 4, 6, 10 }, 17));
        System.out.println(searchFloorOfANumber(new int[] { 4, 6, 10 }, -1));

        System.out.println(searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'f'));
        System.out.println(searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'b'));
        System.out.println(searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'm'));
        System.out.println(searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'h'));

        System.out.println(searchMinDiffElement(new int[] { 4, 6, 10 }, 7));
        System.out.println(searchMinDiffElement(new int[] { 4, 6, 10 }, 4));
        System.out.println(searchMinDiffElement(new int[] { 1, 3, 8, 10, 15 }, 12));
        System.out.println(searchMinDiffElement(new int[] { 4, 6, 10 }, 17));
    }
}
