package com.mylearning.onlinetests.google;

import java.util.HashMap;
import java.util.Map;

public class TotalBox {

    //    Please use this Google doc during your interview (your interviewer will see what you write here). To free your hands for typing, we recommend using a headset or speakerphone.
    //
    //    Given a sequence of boxes, each with 0 or more pens in it and a number K (> 0), choose two non-overlapping intervals of boxes such that:
    //    For each interval, the total number of pens in it is exactly K.
    //    The total number of boxes chosen is as small as possible
    //
    //
    //    Position, Number of pens
    //0, 1      1
    //        1, 2    3
    //        2, 2    5
    //        3, 3    8
    //        4, 4    12
    //        5, 6    18
    //        6, 3    21
    //        7, 1    22
    //        8, 4    26
    //        9, 2    28
    //        10, 8    36
    //
    //    K=5
    //
    //
    //    S(end) - S(start) = K
    //
    //    S(end) = K - S(start)
    //
    //    S(start) = S(end) - K
    // assuming we are sending the total number of boxes chosen in both the intervals

    int getTotalBoxesChosen(int [] boxes, int K){
        // create a sums array which stores sum uptill a given index
        int [] sums = new int[boxes.length];
        fillSum(sums,boxes);
        // initializing an hashmap for fasterlookup of a given sum
        Map<Integer,Integer> sumVsIndex = new HashMap<>();
        fillRunningSumLookup(sumVsIndex,sums);
        int totalBoxes = 0;
        for(int i=0; i < boxes.length -1; i++){
            //lookBack
            int start = -1,end = -1;
            if(sumVsIndex.containsKey(sums[i]-K)){
                // index i is the end for subarrray looking backwards, ending at index i
                start = sumVsIndex.get(sums[i]-K) +1;
            }
            //look forward
            if(sumVsIndex.containsKey(K+ sums[i+1])){
                // index i+1 is the start for any subarray ending later
                end = sumVsIndex.get(K+ sums[i+1]);
            }
            if(start > -1 && end >-1){
                int firstSubArrayLength =  i - start +1;
                int secondSubArrayLength =  end - (i+1) +1;
                int totalLength = firstSubArrayLength + secondSubArrayLength ;
                totalBoxes = totalBoxes > totalLength ? totalLength : totalBoxes;
            }
        }
        return totalBoxes;
    }
    // put running sum of input arr in sum[] array
    void fillSum(int[] sums, int [] arr){
        for(int i= 0; i< arr.length; i++){
            if(i==0){
                sums[0]=arr[i];
            } else{
                sums[i] = arr[i] + sums[i-1];
            }
        }
    }
    void fillRunningSumLookup(Map<Integer, Integer> lookup, int runningSum []){
        for(int i = 0; i< runningSum.length; i++){
            lookup.put(runningSum[i],i);
        }
    }
    //    Time : O(n) + O(n) + O(n)  =  O(N)
    //    Space:  O(n)
    //
    ////test cases
    //
    //    not present
    //    present but window ends at end/start
    //    present but overlapping
    //    multiple windows, should return least window sum of both subarrays
    //    Mix and match of above
    //    a positive case, working
}
