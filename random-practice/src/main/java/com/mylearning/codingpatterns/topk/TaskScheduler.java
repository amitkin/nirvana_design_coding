package com.mylearning.codingpatterns.topk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class TaskScheduler {

    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            // map key is TaskName, and value is number of times to be executed.
            map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1);
        }
        //frequency sort in decreasing order
        PriorityQueue<Entry<Character, Integer>> queue = new PriorityQueue<>(
                (a,b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());

        queue.addAll(map.entrySet());

        int count = 0;
        while (!queue.isEmpty()) {
            int k = n + 1;
            List<Entry> tempList = new ArrayList<>();
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

            if (!queue.isEmpty()) {
                count = count + k; // if k >= 0, then it means we need to be idle
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char[] tasks = new char[] { 'a', 'a', 'a', 'b', 'c', 'c' };
        System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.leastInterval(tasks, 2));

        tasks = new char[] { 'a', 'b', 'a' };
        System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.leastInterval(tasks, 3));
    }
}
