package com.mylearning.codingpatterns.topk;

import java.util.LinkedList;
import java.util.List;

public class KClosestElements {

    ///Two pointer - O(logN+K), Space - O(1)
    public static List<Integer> findClosestElements(int[] arr, int K, int X) {
        List<Integer> result = new LinkedList<>();
        int index = binarySearch(arr, X);
        int leftPointer = index;
        int rightPointer = index + 1;
        for (int i = 0; i < K; i++) {
          if (leftPointer >= 0 && rightPointer < arr.length) {
            int diff1 = Math.abs(X - arr[leftPointer]);
            int diff2 = Math.abs(X - arr[rightPointer]);
            if (diff1 <= diff2)
              result.add(0, arr[leftPointer--]); // append in the beginning
            else
              result.add(arr[rightPointer++]); // append at the end
          } else if (leftPointer >= 0) {
            result.add(0, arr[leftPointer--]);
          } else if (rightPointer < arr.length) {
            result.add(arr[rightPointer++]);
          }
        }
        return result;
    }

    //Binary search - O(logn)
    /*public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        //-------- Main idea behind the binary search algorithm ----------//
        // 1) res will be a consecutive subarray of k size
        // 2) say if we need to pick 4 elems, now we r looking at 5 elem n1, n2, n3, n4, n5
        //    we need to compare two ends: diff(x, n1) and diff(x, n5), the number with bigger diff on the end will be eleminated
        //----------------------------------------------------------------//
        // lo and hi: range of all possible start of subarray with size k + 1, so we could compare both ends
        int lo = 0;
        int hi = arr.length - k - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // for subarray starting at mid with size k+1, we compare element of two ends to eliminate the loser
            if (Math.abs(x - arr[mid]) > Math.abs(x - arr[mid+k])) {
                lo = mid + 1; // arr[mid] is the one further away from x, eliminate range[lo, mid]
            } else {
                hi = mid - 1; // arr[mid+k] is the one further away, all [mid, hi] will have simiar situation, elimiate them
            }
        }
        // return subarray
        List<Integer> res = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            res.add(arr[lo + i]);
        }
        return res;
    }*/

    //O(logN+K∗logK), O(logN) for Binary Search and O(K∗logK) to insert the numbers in the Min Heap, as well as to sort the output array.
    //Space - O(K), as we need to put a maximum of 2K numbers in the heap.
    /*public static List<Integer> findClosestElements(int[] arr, int K, int X) {
        int index = binarySearch(arr, X);
        int low = index - K, high = index + K;
        low = Math.max(low, 0); // 'low' should not be less than zero
        high = Math.min(high, arr.length - 1); // 'high' should not be greater the size of the array

        PriorityQueue<Entry> minHeap = new PriorityQueue<>((n1, n2) -> {
            if(n1.key == n2.key) {
                return n1.value - n2.value;
            } else {
                return n1.key - n2.key;
            }
        });
        // add all candidate elements to the min heap, sorted by their absolute difference from 'X'
        for (int i = low; i <= high; i++)
            minHeap.add(new Entry(Math.abs(arr[i] - X), i));

        // we need the top 'K' elements having smallest difference from 'X'
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < K; i++)
            result.add(arr[minHeap.poll().value]);

        Collections.sort(result);
        return result;
    }

    static class Entry {
        int key;
        int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }*/

    private static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (low > 0) {
            return low - 1;
        }
        return low;
    }

    public static void main(String[] args) {
        List<Integer> result = KClosestElements.findClosestElements(new int[] { 0,0,1,2,3,3,4,7,7,8 }, 3, 5);
        System.out.println("'K' closest numbers to 'X' are: " + result); //[3,3,4]

        result = KClosestElements.findClosestElements(new int[] { 5, 6, 7, 8, 9 }, 3, 7);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = KClosestElements.findClosestElements(new int[] { 2, 4, 5, 6, 9 }, 3, 6);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = KClosestElements.findClosestElements(new int[] { 2, 4, 5, 6, 9 }, 3, 10);
        System.out.println("'K' closest numbers to 'X' are: " + result);
    }
}
