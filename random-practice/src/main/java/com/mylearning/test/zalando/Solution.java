package com.mylearning.test.zalando;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

class Solution {
    ArrayList<Integer> nums;
    HashMap<Integer, Integer> locs;
    java.util.Random rand = new java.util.Random();

    public Solution() {
        nums = new ArrayList<>();
        locs = new HashMap<>();
    }

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            // map key is TaskName, and value is number of times to be executed.
            map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1);
        }
        //frequency sort in decreasing order
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(
                (a,b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());

        queue.addAll(map.entrySet());

        int count = 0;
        while (!queue.isEmpty()) {
            int k = n + 1;
            List<Map.Entry> tempList = new ArrayList<>();
            while (k > 0 && !queue.isEmpty()) {
                Map.Entry<Character, Integer> top = queue.poll(); // most frequency task
                top.setValue(top.getValue() - 1); // decrease frequency, meaning it got executed
                tempList.add(top); // collect task to add back to queue
                k--;
                count++; //successfully executed task
            }

            for (Map.Entry<Character, Integer> e : tempList) {
                if (e.getValue() > 0)
                    queue.add(e); // add valid tasks
            }

            if (queue.isEmpty())
                break;
            count = count + k; // if k >= 0, then it means we need to be idle
        }
        return count;
    }

    public int climbStairs(int n) {
        // base cases
        if(n <= 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;

        int one_step_before = 2;
        int two_steps_before = 1;
        int all_ways = 0;

        for(int i=2; i<n; i++){
            all_ways = one_step_before + two_steps_before;
            two_steps_before = one_step_before;
            one_step_before = all_ways;
        }
        return all_ways;
    }

    public int findKthLargest(int[] nums, int k) {
        return findKth(nums, k, (a, b) -> Integer.compare(b, a));
    }

    private int findKth(int[] nums, int k, Comparator<Integer> cmp) {
        int left = 0, right = nums.length - 1;
        Random r = new Random(0);
        while (left <= right) {
            // Generates a random integer in [left, right].
            int pivotIdx = r.nextInt(right - left + 1) + left;
            int newPivotIdx = partitionAroundPivot(left, right, pivotIdx, nums, cmp);
            if (newPivotIdx == k - 1) {
                return nums[newPivotIdx];
            } else if (newPivotIdx > k - 1) {
                right = newPivotIdx - 1;
            } else { // newPivotIdx < k - 1.
                left = newPivotIdx + 1;
            }
        }

        throw new NoSuchElementException("no k-th node in array A");
    }

    // Partitions A.subList(left, right+1) around pivotIdx, returns the new index
    // of the pivot, newPivotIdx, after partition. After partitioning,
    // A.subList(left, newPivotIdx) contains elements that are "greater than" the
    // pivot, and A.subList(newPivotIdx + 1 , right + 1) contains elements that
    // are "less than" the pivot.
    //
    // Note: "greater than" and "less than" are defined by the Comparator object.
    //
    // Returns the new index of the pivot element after partition.
    private int partitionAroundPivot(int left, int right, int pivotIdx, int[] nums, Comparator<Integer> cmp) {
        int pivotValue = nums[pivotIdx];
        int newPivotIdx = left;

        swap(nums, pivotIdx, right);
        for (int i = left; i < right; ++i) {
            if (cmp.compare(nums[i], pivotValue) < 0) {
                swap(nums, i, newPivotIdx++);
            }
        }
        swap(nums, right, newPivotIdx);
        return newPivotIdx;
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int [] arr = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(s.findKthLargest(arr, 4));
        LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<Integer, Integer>(10, 1.0f, true){
            protected boolean removeEldestEntry(Entry eldest) {
                return size() > 10;
            }
        };

        Set<Integer> currentHeads = new TreeSet<>();
        currentHeads.add(3);
        currentHeads.add(2);
        currentHeads.add(7);
        int max = ((TreeSet<Integer>) currentHeads).last();
        int min = ((TreeSet<Integer>) currentHeads).first();
    }
}
