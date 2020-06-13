package com.mylearning.sorting;

public class MergeSort {
    public void sort(int[] array) {
        int[] helper = new int[array.length];
        mergesort(array, helper, 0, array.length - 1);
    }

    private void mergesort(int[] nums, int[] helper, int start, int end){
        if(end <= start){
            return;
        }
        int mid = start + (end - start) / 2;
        mergesort(nums, helper, start, mid);
        mergesort(nums, helper, mid + 1, end);

        merge(nums, helper, start, mid, end);
    }
    private void merge(int[] nums, int[] helper, int start, int mid, int end){
        /* Copy both halves into a helper array */
        for (int i = start; i <= end; i++) {
            helper[i] = nums[i];
        }
        int left_index = start;
        int right_index = mid+1;

        int sort_index = left_index;

        while(left_index <= mid && right_index <= end){
            if(helper[left_index] <= helper[right_index]){
                nums[sort_index] = helper[left_index];
                left_index++;
            }else{
                nums[sort_index] = helper[right_index];
                right_index++;
            }
            sort_index++;
        }
        while(left_index <= mid){
            nums[sort_index] = helper[left_index];
            left_index++;
            sort_index++;
        }
        while(right_index <= end){
            nums[sort_index++] = helper[right_index++];
        }
    }

    /* A utility function to print array of size n */
    private void printArray(int arr[]) {
        int n = arr.length;
        for (int i=0; i<n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6, 7};

        MergeSort ob = new MergeSort();
        ob.sort(arr);

        System.out.println("Sorted array is");
        ob.printArray(arr);
    }
}