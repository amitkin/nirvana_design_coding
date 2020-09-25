package com.mylearning.array;

import java.util.Arrays;
import java.util.Random;

public class ReservoirSampling {
    Random random;

    ReservoirSampling() {
        random = new Random ();
    }

    //Choose k entries from n numbers. Make sure each number is selected with the probability of k/n
    public int[] reservoirSampling(int k, int[] stream) {
        int[] reservoir = new int[k];
        for (int i = 0; i < k; i++) {
            reservoir[i] = stream[i];
        }

        for(int i = k; i < stream.length; i++) {
            int j = random.nextInt(i+1);
            if (j < k) {
                reservoir[j] = stream[i];
            }
        }
        return reservoir;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        ReservoirSampling reservoirSampling = new ReservoirSampling();
        System.out.println(Arrays.toString(reservoirSampling.reservoirSampling(5, nums)));
    }
}
