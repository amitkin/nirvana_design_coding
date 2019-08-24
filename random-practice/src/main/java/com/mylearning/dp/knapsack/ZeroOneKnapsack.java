package com.mylearning.dp.knapsack;

/*
//https://www.youtube.com/watch?v=8LusJS5-AGo&t=12s
Given two integer arrays to represent weights and profits of ‘N’ items, we need to find a subset of these items which will give us
maximum profit such that their cumulative weight is not more than a given number ‘C’. Each item can only be selected once, which
means either we put an item in the knapsack or we skip it.
*/
class ZeroOneKnapsack {

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        return this.knapsackRecursive(profits, weights, capacity, 0);
    }

    //The time complexity of the above algorithm is exponential O(2^n), where ‘n’ represents the total number of items.
    //The space complexity is O(n). This space will be used to store the recursion stack. Since the recursive algorithm
    //works in a depth-first fashion, which means that we can’t have more than ‘n’ recursive calls on the call stack at any time.
    private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
        // base checks
        if (capacity <= 0 || currentIndex >= profits.length)
            return 0;

        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
        int profit1 = 0;
        if( weights[currentIndex] <= capacity ) {
            profit1 = profits[currentIndex] + knapsackRecursive(profits, weights, capacity - weights[currentIndex],currentIndex + 1);
        }

        // recursive call after excluding the element at the currentIndex
        int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

        return Math.max(profit1, profit2);
    }

    //dp[i][c] = max (dp[i-1][c], profit[i] + dp[i-1][c-weight[i]])
    //dp[i][c] will represent the maximum knapsack profit for capacity ‘c’ calculated from the first ‘i’ items.
    //Time and Space complexity of O(N*C), where ‘N’ represents total items and ‘C’ is the maximum capacity.
    public int solveKnapsackDP(int[] profits, int[] weights, int capacity) {
        // basic checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        int[][] dp = new int[n][capacity + 1];

        // populate the capacity=0 columns, with '0' capacity we have '0' profit
        for(int i=0; i < n; i++)
            dp[i][0] = 0;

        // if we have only one weight, we will take it if it is not more than the capacity
        for(int c=0; c <= capacity; c++) {
            if(weights[0] <= c)
                dp[0][c] = profits[0];
        }

        // process all sub-arrays for all the capacities
        for(int i=1; i < n; i++) {
            for(int c=1; c <= capacity; c++) {
                int profit1= 0, profit2 = 0;
                // include the item, if it is not more than the capacity
                if(weights[i] <= c)
                    profit1 = profits[i] + dp[i-1][c-weights[i]];
                // exclude the item
                profit2 = dp[i-1][c];
                // take maximum
                dp[i][c] = Math.max(profit1, profit2);
            }
        }

        printSelectedElements(dp, weights, profits, capacity);

        // maximum profit will be at the bottom-right corner.
        return dp[n-1][capacity];
    }

    //How can we find the selected items?
    private void printSelectedElements(int dp[][], int[] weights, int[] profits, int capacity){
        System.out.print("Selected weights:");
        int totalProfit = dp[weights.length-1][capacity];
        for(int i=weights.length-1; i > 0; i--) {
            if(totalProfit != dp[i-1][capacity]) {
                System.out.print(" " + weights[i]);
                capacity -= weights[i];
                totalProfit -= profits[i];
            }
        }

        if(totalProfit != 0)
            System.out.print(" " + weights[0]);
        System.out.println("");
    }

    //Can we improve our bottom-up DP solution even further? Can you find an algorithm that has O(C)O(C)O(C) space complexity?
    //The solution above is similar to the previous solution, the only difference is that we use i%2 instead if i and (i-1)%2 instead if i-1.

    static int solveKnapsack1D(int[] profits, int[] weights, int capacity) {
        // basic checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        int[] dp = new int[capacity + 1];

        // if we have only one weight, we will take it if it is not more than the
        // capacity
        for (int c = 0; c <= capacity; c++) {
            if (weights[0] <= c)
                dp[c] = profits[0];
        }

        // process all sub-arrays for all the capacities
        for (int i = 1; i < n; i++) {
            for (int c = capacity; c >= 0; c--) {
                int profit1 = 0, profit2 = 0;
                // include the item, if it is not more than the capacity
                if (weights[i] <= c)
                    profit1 = profits[i] + dp[c - weights[i]];
                // exclude the item
                profit2 = dp[c];
                // take maximum
                dp[c] = Math.max(profit1, profit2);
            }
        }

        return dp[capacity];
    }


    public static void main(String[] args) {
        ZeroOneKnapsack ks = new ZeroOneKnapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsackDP(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsackDP(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}

