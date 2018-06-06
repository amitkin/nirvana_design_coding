package com.mylearning;

public class QuickSort {
    public void sort(int s[], int lo, int hi){
        if(lo >= hi)
            return;
        int partitionindex = partition(s, lo, hi);
        sort(s,lo, partitionindex-1);
        sort(s,partitionindex+1,hi);
    }

    private int partition(int s[], int lo, int hi){
        int pivot, partitionindex,i;
        pivot = hi;
        partitionindex = lo;

        for(i=lo+1; i<hi; i++)
        {
            if(s[i]<=s[pivot])
            {
                swap(s, i, partitionindex);
                partitionindex = partitionindex + 1;
            }
        }
        swap(s, partitionindex, pivot); //swap with pivot
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
