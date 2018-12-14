package com.mylearning.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
Implement an algorithm that takes as input an array of distinct elements and a size,
and returns a subset of the given size of the array elements. All subsets should be
equally likely. Return the result in input array itself.
*/
public class RandomSampling {

    public static void randomsampling(int k, List<Integer> A) {
        Random gen = new Random ();
        for (int i = 0; i < k; ++i) {
            // Generate a random int in [i, A.size() - 1].
            Collections.swap (A , i, i + gen.nextInt(A . size () - i));
        }
    }

    public static void main(String[] args) {
        List<Integer> num = Arrays.asList(1, 2, 3);
        randomsampling(2, num);
        System.out.println(num);
    }
}
