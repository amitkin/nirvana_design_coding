package com.mylearning.leetcode;

import java.util.Arrays;

public class OddNumberTimes {
    static int max_ref;

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 3, 1, 3};
        System.out.println("Odd occurences : " + getOddOccurences(arr));

        reverseArray(arr, 0, arr.length - 1);
        System.out.println("Reversed array : " + Arrays.toString(arr));

        printLeaders(arr);

        int[] arr1 = { 10, 22, 9, 33, 21, 50, 41, 60 };
        int n = arr1.length;
        System.out.println("Length of list is " + lis(arr1, n));
    }

    static int getOddOccurences(int[] array){
        int size = array.length;
        int res = 0;
        for (int i : array) {
            res = res ^ i;
        }
        return res;
    }

    static void reverseArray(int[] arr, int start, int end){
        int temp;
        if(start >= end)
            return;

        temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        reverseArray(arr, start+1, end-1);
    }

    static void printLeaders(int[] arr) {
        int size = arr.length;
        int max_from_right = arr[size - 1];
        System.out.print(max_from_right);
        for (int i = size-2; i >= 0; i--) {
            if(max_from_right < arr[i]){
                System.out.print(" " + arr[i]);
                max_from_right = arr[i];
            }
        }
        System.out.println("");
    }

    static int lis(int arr[],int n)
    {
        int lis[] = new int[n];
        int i,j,max = 0;

        /* Initialize LIS values for all indexes */
        for ( i = 0; i < n; i++ )
            lis[i] = 1;

        /* Compute optimized LIS values in bottom up manner */
        for ( i = 1; i < n; i++ )
            for ( j = 0; j < i; j++ )
                if ( arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;

        /* Pick maximum of all LIS values */
        for ( i = 0; i < n; i++ )
            if ( max < lis[i] )
                max = lis[i];

        return max;
    }
}
