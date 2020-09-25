package com.mylearning.array;

import java.util.Random;

public class MaximalNumberSampling {

    private Random random;

    MaximalNumberSampling() {
        random = new Random();
    }
    public int findMaxIndex(int[] a) {
        int count = 1;
        int maxElement = Integer.MIN_VALUE;
        int maxIndex = Integer.MIN_VALUE;
        for(int i = 0; i < a.length; i++) {
            if(a[i] == maxElement) {
                count++;
                if(random.nextInt() < 1/count) { //random number in the range of [0,1]
                    maxIndex = i;
                }
            } else if(a[i] > maxElement) {
                count = 1;
                maxElement = a[i];
            }
        }

        return maxIndex;
    }

    public static void main(String[] args) {
        MaximalNumberSampling maximalNumberSampling = new MaximalNumberSampling();
        System.out.println(maximalNumberSampling.findMaxIndex(new int[]{1,2,3,3,3}));
    }

}
