package com.mylearning.dp;

public class MaximumCoins {

    static int maximumCostDP(int[][] mat) {
        int maxSum = -1;

        int m = mat.length; //length
        int n = mat[0].length; //width

        int[][] dp = new int[m][n];

        for(int i=0;i<m;i++)
            dp[i][0]=mat[i][0]; //initialising first column

        for(int i=0; i<m; i++) {
            for (int j=1; j<n; j++) {
                if(i==0){
                    dp[i][j]=Math.max(dp[i][j-1],dp[i+1][j-1])+mat[i][j];  // case where element is first in column
                }
                else if(i==m-1){
                    dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j-1])+mat[i][j];  // case where element is last in column
                }
                else{
                    dp[i][j]=Math.max(dp[i][j-1],Math.max(dp[i-1][j-1],dp[i+1][j-1]))+mat[i][j];    // all the rest elements
                }
            }
        }

        // finding maximum among last column of dp
        for(int i=0;i<n;i++){
            if(maxSum<dp[m-1][i])
                maxSum=dp[m-1][i];
        }

        return maxSum;
    }

    static int maximumCost(int[][] mat) {
        int sum = 0;
        int[][] dirs = new int[][]{{-1, 1}, {0, 1}, {1, 1}};

        int m = mat.length; //length
        int n = mat[0].length; //width

        for(int i=0; i<m; i++){
            int current = traverse(mat, i, 0, mat[i][0], dirs, "");
            System.out.println("Maximum coins starting with (" + i + " ," + 0 + ") is " + current);
            sum = Math.max(sum, current);
        }
        return sum;
    }

    static int traverse(int[][] mat, int i, int j, int sum, int[][] dirs, String indent) {
        System.out.println(indent + "traverse(" + i + ", " + j + ") sum = " + sum);
        int m = mat.length;
        int n = mat[0].length;

        //base condition
        if(j == n && i >= 0 && i < m){
            return sum;
        }

        //recursion
        int result = 0;
        for(int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if(isValid(x, y, m, n)) {
                int current = traverse(mat, x, y, mat[x][y], dirs, indent + "    ");
                result = Math.max(result, current);
            }
        }
        return sum + result;
    }

    static boolean isValid(int i, int j, int m, int n) {
        if(i>=0 && i<m && j>=0 && j<n)
            return true;
        return false;
    }

    public static void main(String[] args) {
        //int matrix[][] = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 }, { 10, 13, 14, 17, 24 }, { 18, 21, 23, 26, 30 } };

        //int matrix[][] = { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 } };

        int matrix[][] = { { 1, 1, 1, 1, 1 }, { 1, 2, 1, 1, 1 }, { 1, 1, 3, 1, 1 }, { 1, 1, 1, 4, 1 }, { 1, 1, 1, 1, 5 } };

        //int matrix[][] = { { 1, 1, 1 }, { 1, 2, 1 }, { 1, 1, 3 } };

        System.out.println("Maximum coins which can be collected is " + maximumCost(matrix));
        System.out.println("Maximum coins using DP which can be collected is " + maximumCostDP(matrix));
    }
}

