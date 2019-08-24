package com.mylearning.onlinetests.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test2 {

    /*
    1. Sorted both backgroundAppList and foregroundAppList on the basis of memory
    2. Then we used 2 pointer approach where
        2a. first pointer pointing to foregroundAppList and second pointer pointing to end of backgroundAppList
        2b. then we compare foreground app memory + background app memory with deviceCapacity and skip all that makes memory greater than deviceCapacity
        2b. for the combination which is equal to device memory we keep on adding to result for all the combinations
        2c. we also collect combinations as backup for which device memory is less
        2c. if we dont get combination for equal to device memory then we return the backup i.e. optimal solution which is for less than device memory.
    */
    public List<List<Integer>> optimalUtilization(int deviceCapacity, List<List<Integer>> foregroundAppList, List<List<Integer>> backgroundAppList){
        Comparator<List<Integer>> comparator = (a, b) -> a.get(1) - b.get(1);

        Collections.sort(foregroundAppList, comparator);
        Collections.sort(backgroundAppList, comparator);

        List<List<Integer>> result = new ArrayList<>();
        int lo1 = 0;
        int hi1 = foregroundAppList.size()-1;
        int lo2 = 0;
        int hi2 = backgroundAppList.size() -1;
        List<Integer> backup = null;
        Integer bestTillNow = null;
        while(lo1 <= hi1 && hi2 >= lo2){
            if(foregroundAppList.get(lo1).get(1) + backgroundAppList.get(hi2).get(1) > deviceCapacity){
                hi2--;
            }else if(foregroundAppList.get(lo1).get(1) + backgroundAppList.get(hi2).get(1) == deviceCapacity){
                result.add(Arrays.asList(foregroundAppList.get(lo1).get(0), backgroundAppList.get(hi2).get(0)));
                while(lo1 <= hi1 -1 && foregroundAppList.get(lo1).get(1) == foregroundAppList.get(lo1+1).get(1)){
                    result.add(Arrays.asList(foregroundAppList.get(lo1+1).get(0), backgroundAppList.get(hi2).get(0)));
                    lo1++;
                }
                while(hi2 >= lo2 +1 && backgroundAppList.get(hi2).get(1) == backgroundAppList.get(hi2-1).get(1)){
                    result.add(Arrays.asList(foregroundAppList.get(lo1).get(0), backgroundAppList.get(hi2-1).get(0)));
                    hi2--;
                }
                lo1++;
                hi2--;
            }else {
                if(backup != null &&  (deviceCapacity - foregroundAppList.get(lo1).get(1) + backgroundAppList.get(hi2).get(1)) < bestTillNow){
                    backup.set(0, foregroundAppList.get(lo1).get(0));
                    backup.set(1, backgroundAppList.get(hi2).get(0));
                }
                else {
                    backup = new ArrayList<>();
                    backup.add(foregroundAppList.get(lo1).get(0));
                    backup.add(backgroundAppList.get(hi2).get(0));
                    bestTillNow = deviceCapacity - foregroundAppList.get(lo1).get(1) + backgroundAppList.get(hi2).get(1);
                }
                lo1++;
            }

        }

        if(result.isEmpty()){
            if(backup != null) {
                result.add(backup);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Test2 a = new Test2();
        System.out.println(a.optimalUtilization(7, Arrays.asList(Arrays.asList(1,2),Arrays.asList(2,4),Arrays.asList(3,6)), Arrays.asList(Arrays.asList(1,2))));
        System.out.println(a.optimalUtilization(10, Arrays.asList(Arrays.asList(1,3), Arrays.asList(2,5), Arrays.asList(3,7), Arrays.asList(4,10)), Arrays.asList(Arrays.asList(1,2), Arrays.asList(2,3), Arrays.asList(3,4), Arrays.asList(4,5))));
    }
}
