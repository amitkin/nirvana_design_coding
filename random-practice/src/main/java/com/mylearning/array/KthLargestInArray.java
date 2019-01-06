package com.mylearning.array;

import java.util.NoSuchElementException;
import java.util.Random;

public class KthLargestInArray {
    private static int findKth(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        Random r = new Random(0);
        while (left <= right) {
            // Generates a random integer in [left, right].
            int pivotIdx = r.nextInt(right - left + 1) + left;
            int newPivotIdx = partitionAroundPivot(left, right, pivotIdx, nums);
            if (newPivotIdx == k - 1) {
                return nums[newPivotIdx];
            } else if (newPivotIdx < k - 1) {
                left = newPivotIdx + 1;
            } else {
                right = newPivotIdx - 1;
            }
        }

        throw new NoSuchElementException("no k-th node in array A");
    }

    // Partitions arr(left, right+1) around pivotIdx, returns the new index
    // of the pivot, newPivotIdx, after partition. After partitioning,
    // arr(left, newPivotIdx) contains elements that are "greater than" the
    // pivot, and arr(newPivotIdx + 1 , right + 1) contains elements that
    // are "less than" the pivot.
    //
    // Note: "greater than" and "less than" are defined by the Comparator object.
    //
    // Returns the new index of the pivot element after partition.
    private static int partitionAroundPivot(int left, int right, int pivotIdx, int[] nums) {
        int pivotValue = nums[pivotIdx];
        int newPivotIdx = left;

        swap(nums, pivotIdx, right);
        for (int i = left; i < right; ++i) {
            if (nums[i] >= pivotValue) {
                swap(nums, i, newPivotIdx++);
            }
        }
        swap(nums, right, newPivotIdx);
        return newPivotIdx;
    }

    private static void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int [] arr = new int[]{3,1,-1,2};
        //int [] arr = new int[]{3,2,3,1,2,4,5,5,6};
        int k = 2;
        System.out.println(findKth(arr, k));
    }
}
