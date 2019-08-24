package com.mylearning.dp.knapsack;

/*Given a set of positive numbers, partition the set into two subsets with minimum difference between their subset sums.*/
public class MinimumSubsetSumDifference {

    public int canPartition(int[] num) {
        return this.canPartitionRecursive(num, 0, 0, 0);
    }

    private int canPartitionRecursive(int[] num, int currentIndex, int sum1, int sum2) {
        // base check
        if (currentIndex == num.length)
            return Math.abs(sum1 - sum2);

        // recursive call after including the number at the currentIndex in the first set
        int diff1 = canPartitionRecursive(num, currentIndex+1, sum1+num[currentIndex], sum2);

        // recursive call after including the number at the currentIndex in the second set
        int diff2 = canPartitionRecursive(num, currentIndex+1, sum1, sum2+num[currentIndex]);

        return Math.min(diff1, diff2);
    }

    /*
    Let’s assume ‘S’ represents the total sum of all the numbers. So, in this problem, we are trying to find a subset whose sum is as close
    to ‘S/2’ as possible, because if we can partition the given set into two subsets of an equal sum, we get the minimum difference, i.e. zero.
    This transforms our problem to Subset Sum, where we try to find a subset whose sum is equal to a given number-- ‘S/2’ in our case. If we
    can’t find such a subset, then we will take the subset which has the sum closest to ‘S/2’. This is easily possible, as we will be calculating
    all possible sums with every subset.
    Time and space complexity of O(N∗S), where ‘N’ represents total numbers and ‘S’ is the total sum of all the numbers.
    */
    public int canPartitionDP(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
            sum += num[i];

        int n = num.length;
        boolean[][] dp = new boolean[n][sum/2 + 1];

        // populate the sum=0 columns, as we can always form '0' sum with an empty set
        for(int i=0; i < n; i++)
            dp[i][0] = true;

        // with only one number, we can form a subset only when the required sum is equal to that number
        for(int s=1; s <= sum/2 ; s++) {
            dp[0][s] = (num[0] == s ? true : false);
        }

        // process all subsets for all sums
        for(int i=1; i < num.length; i++) {
            for(int s=1; s <= sum/2; s++) {
                // if we can get the sum 's' without the number at index 'i'
                if(dp[i-1][s]) {
                    dp[i][s] = dp[i-1][s];
                } else if (s >= num[i]) {
                    // else include the number and see if we can find a subset to get the remaining sum
                    dp[i][s] = dp[i-1][s-num[i]];
                }
            }
        }

        int sum1 = 0;
        // Find the largest index in the last row which is true
        for (int i = sum / 2; i >= 0; i--) {
            if (dp[n-1][i] == true) {
                sum1 = i;
                break;
            }
        }

        int sum2 = sum - sum1;
        return Math.abs(sum2-sum1);
    }

    public static void main(String[] args) {
        MinimumSubsetSumDifference ps = new MinimumSubsetSumDifference();
        int[] num = {1, 2, 3, 9};
        System.out.println(ps.canPartitionDP(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ps.canPartitionDP(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(ps.canPartitionDP(num));
    }
}
