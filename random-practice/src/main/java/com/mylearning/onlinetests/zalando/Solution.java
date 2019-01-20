package com.mylearning.onlinetests.zalando;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    ArrayList<Integer> nums;
    HashMap<Integer, Integer> locs;

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

    public static void main(String[] args) {
        Solution s = new Solution();
    }
}
