package com.mylearning.dp.knapsack;

/*You are given a set of positive numbers and a target sum ‘S’. Each number should be assigned either a ‘+’ or ‘-’ sign.
We need to find the total ways to assign symbols to make the sum of the numbers equal to the target ‘S’.
*/
public class TargetSumPlusMinus {

    public int findTargetSubsets(int[] num, int s) {
        int totalSum = 0;
        for (int n : num)
            totalSum += n;

        // if 's + totalSum' is odd, we can't find a subset with sum equal to '(s + totalSum) / 2'
        // Time and space complexity of O(N*S), where ‘N’ represents total numbers and ‘S’ is the desired sum.
        if(totalSum < s || (s + totalSum) % 2 == 1)
            return 0;

        return countSubsets(num, (s + totalSum) / 2);
    }

    // Its similar to find two subsets of the given numbers whose difference is equal to the given target ‘S’.
    // Input: {1, 1, 2, 3}, S=1, {+1-1-2+3}, (1 + 3) - (1 + 2 ) = 1
    // Sum(s1) - Sum(s2) = S, Sum(s1) + Sum(s2) = Sum(num), solving these two equations we get Sum(s1) = (S + Sum(num)) / 2
    // This essentially converts our problem to: Find the count of subsets of the given numbers whose sum is equal to (S + Sum(num)) / 2
    private int countSubsets(int[] num, int sum) {
        int n = num.length;
        int[][] dp = new int[n][sum + 1];

        // populate the sum=0 columns, as we will always have an empty set for zero sum
        for(int i=0; i < n; i++)
            dp[i][0] = 1;

        // with only one number, we can form a subset only when the required sum is equal to the number
        for(int s=1; s <= sum ; s++) {
            dp[0][s] = (num[0] == s ? 1 : 0);
        }

        // process all subsets for all sums
        for(int i=1; i < num.length; i++) {
            for(int s=1; s <= sum; s++) {
                dp[i][s] = dp[i-1][s];
                if(s >= num[i])
                    dp[i][s] += dp[i-1][s-num[i]];
            }
        }

        // the bottom-right corner will have our answer.
        return dp[num.length-1][sum];
    }

    public static void main(String[] args) {
        TargetSumPlusMinus ts = new TargetSumPlusMinus();
        int[] num = {1, 1, 2, 3};
        System.out.println(ts.findTargetSubsets(num, 1));
        num = new int[]{1, 2, 7, 1};
        System.out.println(ts.findTargetSubsets(num, 9));
    }
}
