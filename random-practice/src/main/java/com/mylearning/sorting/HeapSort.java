package com.mylearning.sorting;

public class HeapSort {
    private void sort(int arr[]) {
        int n = arr.length;

        // Build heap (rearrange array) O(n)
        for (int i = n / 2 - 1; i >= 0; i--)
            max_heapify(arr, n, i);

        // One by one extract an element from heap in bottom up manner
        // h*n/2 + (h-1)*n/4 + ... + 0 * 1, where h = log n
        for (int i=n-1; i>0; i--) {
            //Initially height is h, so n/2 items deleted take h*n/2 time for max_heapify
            //Then height becomes h-1 and n/4 items deleted at that height and so on
            deleteMax(arr, i);

            // call max_heapify on the reduced heap size with i (needs to be placed correctly) as root - O(log n)
            max_heapify(arr, i, 0);
        }
    }

    private void deleteMax(int arr[], int i) {
        // Move current root to end - meaning maximum is moved to end so ascending order
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;
    }

    // To max_heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap - O(log n)
    private void max_heapify(int arr[], int n, int i) {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively max_heapify the affected sub-tree
            max_heapify(arr, n, largest);
        }
    }

    /* A utility function to print array of size n */
    private void printArray(int arr[]) {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6, 7};

        HeapSort ob = new HeapSort();
        ob.sort(arr);

        System.out.println("Sorted array is");
        ob.printArray(arr);
    }
}
