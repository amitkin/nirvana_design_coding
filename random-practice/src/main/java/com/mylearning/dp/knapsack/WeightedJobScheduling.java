package com.mylearning.dp.knapsack;

import java.util.Arrays;

/*
Given N jobs where every job is represented by following three elements of it.
Start Time
Finish Time
Profit or Value Associated

Find the maximum profit subset of jobs such that no two jobs in the subset overlap.
*/
public class WeightedJobScheduling {

    /*
    The algorithm is:
    Sort the jobs by non-decreasing finish times.
    For each i from 1 to n, determine the maximum value of the schedule from the subsequence of jobs[0..i].
    Do this by comparing the inclusion of job[i] to the schedule to the exclusion of job[i] to the schedule, and then taking the max.
    To find the profit with inclusion of job[i], we need to find the latest job that doesn’t conflict with job[i].
    The idea is to use Binary Search to find the latest non-conflicting job.
    */

    private static class Job
    {
        int start, finish, profit;

        Job(int[] arr) {
            this.start = arr[0];
            this.finish = arr[1];
            this.profit = arr[2];
        }
    }

    //Time complexity - O(nlgn)
    private static int findMaxProfit(int[][] arr)
    {
        int n = arr.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i<n; i++) {
            Job job = new Job(arr[i]);
            jobs[i] = job;
        }

        // Sort jobs according to finish time
        Arrays.sort(jobs, (s1, s2) -> Integer.compare(s1.finish, s2.finish));

        // Create an array to store solutions of subproblems.
        // table[i] - stores the profit for jobs till jobs[i] (including jobs[i])
        int[] table = new int[n];
        table[0] = jobs[0].profit;

        // Fill entries in table[] using recursive property
        for (int i=1; i<n; i++)
        {
            // Find profit including the current job
            int inclProf = jobs[i].profit;
            int l = binarySearch(jobs, i);
            if (l != -1)
                inclProf += table[l];

            // Store maximum of including and excluding
            table[i] = Math.max(inclProf, table[i-1]);
        }

        // Store result and free dynamic memory allocated for table[]
        return table[n-1];
    }

    // A Binary Search based function to find the latest job
    // (before current job) that doesn't conflict with current
    // job.  "index" is index of the current job.  This function
    // returns -1 if all jobs before index conflict with it.
    // The array jobs[] is sorted in increasing order of finish time.
    private static int binarySearch(Job[] jobs, int index)
    {
        // Initialize 'lo' and 'hi' for Binary Search
        int lo = 0, hi = index - 1;

        // Perform binary Search iteratively
        while (lo <= hi)
        {
            int mid = (lo + hi) / 2;
            if (jobs[mid].finish <= jobs[index].start)
            {
                if (jobs[mid + 1].finish <= jobs[index].start)
                    lo = mid + 1;
                else
                    return mid;
            }
            else
                hi = mid - 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] arr = {{3, 10, 20}, {1, 2, 50}, {6, 19, 100}, {2, 100, 200}};
        System.out.println("Optimal profit is " + findMaxProfit(arr));
    }
}
