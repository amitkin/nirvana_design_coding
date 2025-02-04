package com.mylearning.array;

import java.util.Arrays;

public class NextPermutation {
    /*
    1. Start from its last element, traverse backward to find the first one with index i that satisfy num[i-1] < num[i]. So,
    elements from num[i] to num[n-1] is reversely sorted.
    2. To find the next permutation, we have to swap some numbers at different positions, to minimize the increased amount, we
    have to make the highest changed position as high as possible. Notice that index larger than or equal to i is not possible
    as num[i,n-1] is reversely sorted. So, we want to increase the number at index i-1, clearly, swap it with the smallest
    number between num[i,n-1] that is larger than num[i-1]. For example, original number is 121543321, we want to swap the '1'
    at position 2 with '2' at position 7.
    3. The last step is to make the remaining higher position part as small as possible, we just have to reversely sort the num[i,n-1]
    */
    public void nextPermutation(int[] num) {
        int n=num.length;
        if(n<2)
            return;
        int index=n-1;
        while(index>0){
            if(num[index-1]<num[index])
                break;
            index--;
        }
        if(index==0){
            reverseSort(num,0,n-1);
            return;
        }
        else{
            int val=num[index-1];
            int j=n-1;
            while(j>=index){
                if(num[j]>val)
                    break;
                j--;
            }
            swap(num,j,index-1);
            reverseSort(num,index,n-1);
            return;
        }
    }

    public void swap(int[] num, int i, int j){
        int temp=0;
        temp=num[i];
        num[i]=num[j];
        num[j]=temp;
    }

    public void reverseSort(int[] num, int start, int end){
        if(start>end)
            return;
        for(int i=start;i<=(end+start)/2;i++)
            swap(num,i,start+end-i);
    }

    public static void main(String[] args) {
        NextPermutation n = new NextPermutation();
        int[] num = new int[]{3,2,1};
        n.nextPermutation(num);
        System.out.println(Arrays.toString(num));
    }
}
