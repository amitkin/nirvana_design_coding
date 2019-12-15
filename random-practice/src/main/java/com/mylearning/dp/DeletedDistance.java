package com.mylearning.dp;

public class DeletedDistance {
    /*
    "dog"
     ^
    "frog"
     ^

    dp(i, j) : min delete distance of str1(0:i) and str2(0:j)
    dp(i, j) = if(str1.charAt(i) == str2.charAt(j))
                  dp(i-1, j-1)
               else
                  min(dp(i -1, j), dp(i, j-1)) + 1;

       "" f r o g
    "" 0  1 2 3 4
    d  1  2 3 4 5
    o  2  3 4 3 4
    g  3  4 5 4 3

    run time: O(m * n)
    space: O(m * n)
    */

    static int deletionDistance(String str1, String str2) {
        // your code goes here

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        // Fill d[][] in bottom up manner
        for(int i=0; i<=str1.length(); i++) {
            dp[i][0] = i;
        }

        for(int j=0; j<=str2.length(); j++) {
            dp[0][j] = j;
        }

        for(int i=1; i<=str1.length(); i++) {
            for(int j=1; j<=str2.length(); j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                    //https://leetcode.com/problems/cherry-pickup/
                }

            }
        }

        return dp[str1.length()][str2.length()];
    }

    static int deletionDistance(String str1 , String str2, int m, int n) {
        // If first string is empty, the only option is to
        // delete all characters of second string
        if (m == 0) return n;

        // If second string is empty, the only option is to
        // delete all characters of first string
        if (n == 0) return m;

        // If last characters of two strings are same, nothing
        // much to do. Ignore last characters and get deleteDistance for
        // remaining strings.
        if (str1.charAt(m-1) == str2.charAt(n-1))
            return deletionDistance(str1, str2, m-1, n-1);

        // If last characters are not same, consider all the
        // operations on last character of first string, recursively
        // compute minimum cost for all the operations and take
        // minimum of all the values.
        return 1 + Math.min(deletionDistance(str1,  str2, m, n-1), deletionDistance(str1,  str2, m-1, n));
    }

    public static void main(String[] args) {
        System.out.println(deletionDistance("dog", "frog")); //3
        System.out.println(deletionDistance("dog", "frog", 3, 4));
    }
}
