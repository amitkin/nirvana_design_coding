package com.mylearning.sorting;

import java.util.Random;

public class QuickSort {

    private Random random = new Random(0);

    public void sort(int s[], int lo, int hi){
        if(lo > hi)
            return;
        // Generates a random integer in [lo, hi]
        int pivotIdx = random.nextInt(hi - lo + 1) + lo;

        int partitionindex = partitionAroundPivot(s, lo, hi, pivotIdx);
        sort(s,lo, partitionindex-1);
        sort(s,partitionindex+1,hi);
    }

    private int partitionAroundPivot(int s[], int lo, int hi, int pivotIdx){
        int pivotValue = s[pivotIdx];
        int partitionindex = lo;

        //swap pivot value i.e. pivotIdx with hi value
        swap(s, pivotIdx, hi);

        for(int i=lo; i<hi; i++)
        {
            if(s[i] < pivotValue)
            {
                swap(s, i, partitionindex);
                partitionindex = partitionindex + 1;
            }
        }
        swap(s, partitionindex, hi); //swap hi i.e. pivot value with partitionindex value
        return partitionindex;
    }

    private void swap(int s[], int x, int y)
    {
        int t;
        t=s[x];
        s[x]=s[y];
        s[y]=t;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int arr[] = {10, 7, 8, 9, 1, 5};
        int n = arr.length;
        quickSort.sort(arr,0, n-1);
        for(int i: arr){
            System.out.println(i);
        }
    }
}
