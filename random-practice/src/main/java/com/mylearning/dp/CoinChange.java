package com.mylearning.dp;

import java.util.HashMap;
import java.util.Map;

public class CoinChange {

    /*
    You are given coins of different denominations and a total amount of money amount. Write a function to compute
    the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by
    any combination of the coins, return -1.
    */
    public int coinChange(int[] coins, int amount) {

        if (coins == null || coins.length == 0 || amount <= 0) {
            return 0;
        }
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
        if (minCoinChange[amount] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minCoinChange[amount];
        }
    }

    public int coinChangeMemoization(int[] coins, int amount) {
        Map<Integer,Integer> amountVsCoins = new HashMap<>();
        return getCoins(coins,amount,amountVsCoins);
    }

    private int getCoins(int [] denominations, int amount, Map<Integer,Integer> amountVsCoins){
        if(amountVsCoins.containsKey(amount)){
            return amountVsCoins.get(amount);
        }
        if(amount == 0){
            return 0;
        }
        else {
            int minCoins = Integer.MAX_VALUE;
            //loop for every denomination
            for(int i=0; i< denominations.length; i++){
                if(denominations[i]<=amount && denominations[i] > 0){
                    int maxNumberPossible = amount/denominations[i];
                    int denominationValue = denominations[i];
                    // repeat every possible number count of this denomination
                    // because we are having all cases of denomination[i] here, we wont let have recursive calls have this denomination for calculation
                    denominations[i] = -1;
                    /*for(int j = 0; j<= maxNumberPossible; j++){
                        int coins = getCoins(denominations, amount - j * denominationValue , amountVsCoins);
                        if(coins >= 0 && j + coins < minCoins){
                            minCoins = j + coins;
                        }
                    }*/
                    denominations[i] = denominationValue;
                }
            }
            if(minCoins == Integer.MAX_VALUE){
                return -1;
            }else{
                amountVsCoins.put(amount,minCoins);
                return minCoins;
            }
        }
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange(new int[]{261,411,27,78,61}, 5456));
        System.out.println(coinChange.coinChangeMemoization(new int[]{261,411,27,78,61}, 5456));
    }
}
