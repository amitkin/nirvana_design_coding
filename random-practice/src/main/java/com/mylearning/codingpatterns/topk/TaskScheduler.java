package com.mylearning.codingpatterns.topk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/*
You are given a list of tasks that need to be run, in any order, on a server. Each task will take one CPU interval
to execute but once a task has finished, it has a cooling period during which it can’t be run again. If the cooling
period for all tasks is ‘K’ intervals, find the minimum number of CPU intervals that the server needs to finish all tasks.

If at any time the server can’t execute any task then it must stay idle.

Example 1:
Input: [a, a, a, b, c, c], K=2
Output: 7
Explanation: a -> c -> b -> a -> c -> idle -> a

Example 2:
Input: [a, b, a], K=3
Output: 5
Explanation: a -> b -> idle -> idle -> a

Solution : We need to rearrange tasks such that same tasks are ‘K’ distance apart.
Following a similar approach, we will use a Max Heap to execute the highest frequency task first. After executing a task
we decrease its frequency and put it in a waiting list. In each iteration, we will try to execute as many as k+1 tasks.
For the next iteration, we will put all the waiting tasks back in the Max Heap. If, for any iteration, we are not able
to execute k+1 tasks, the CPU has to remain idle for the remaining time in the next iteration.
*/
public class TaskScheduler {

    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for(char task : tasks) {
            // map key is TaskName, and value is number of times to be executed.
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        //frequency sort in decreasing order
        PriorityQueue<Entry<Character, Integer>> queue = new PriorityQueue<>(
                (a,b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());

        queue.addAll(map.entrySet());

        int count = 0;
        while (!queue.isEmpty()) {
            int k = n + 1;
            List<Entry<Character, Integer>> waitingList = new ArrayList<>();
            while (k > 0 && !queue.isEmpty()) {
                Map.Entry<Character, Integer> top = queue.poll(); // most frequency task
                top.setValue(top.getValue() - 1); // decrease frequency, meaning it got executed
                waitingList.add(top); // collect task to add back to queue
                k--;
                count++; //successfully executed task
            }

            for (Map.Entry<Character, Integer> e : waitingList) {
                if (e.getValue() > 0)
                    queue.add(e); // add valid tasks
            }

            //This is important don't check for k>=0 here. Idle will be there only when there is something to process in next runs
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
