package com.mylearning.dp;

public class CoinChange {

    /*
    You are given coins of different denominations and a total amount of money amount. Write a function to compute
    the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by
    any combination of the coins, return -1.
    */
    public int coinChange(int[] coins, int amount) {

        if (coins == null || coins.length == 0 || amount <= 0)
            return 0;
        int[] minCoinChange = new int[amount + 1];
        minCoinChange[0] = 0;
        for (int i = 1; i <= amount; i++) {
            minCoinChange[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i && minCoinChange[i - coins[j]] != Integer.MAX_VALUE) {
                    minCoinChange[i] = Math.min(minCoinChange[i], 1 + minCoinChange[i - coins[j]]);
                }
            }
        }
        if (minCoinChange[amount] == Integer.MAX_VALUE)
            return -1;
        else
            return minCoinChange[amount];
    }
}
