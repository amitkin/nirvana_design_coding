package com.mylearning;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.mylearning.epi.EvenOddArray;

public class OddEvenSeggregate{

    private void reverse(int [] list, int begin, int end){

        int p = begin;
        int q = end - 1;
        while(p<q){
            int temp = list[q];
            list[q]  = list[p];
            list[p]  = temp;
            p = p + 1;
            q = q - 1;
        }
    }

    private int swap(int [] list, int begin, int mid, int end){
        reverse(list, begin, mid);
        reverse(list, mid, end);
        reverse(list, begin, end);
        return (end-(mid-begin));
    }


    private int partition(int [] list, int begin, int end){

        if(end - begin>1){
            int mid = (begin+end)/2;
            int p = partition(list, begin, mid);
            int q = partition(list, mid, end);
            mid = swap(list, p, mid, q);
            return mid;
        }

        return list[begin]%2 !=0 ? begin: begin+1;
    }

    private int [] sort(int [] arr){
        this.partition(arr, 0, arr.length);
        return arr;
    }

    public static void main(String [] args){
        int [] arr = {1,2,3,4,5,6,7,8};
        OddEvenSeggregate test = new OddEvenSeggregate();
        System.out.println(Arrays.toString(test.sort(arr)));
    }
}
