package com.mylearning.recursion;

public class MaximumCoins {

    static int maximumCost(int[][] mat) {
        int sum = 0;
        int[][] dirs = new int[][]{{-1, 1}, {0, 1}, {1, 1}};
        for(int i=0; i<mat.length; i++){
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

        System.out.println("Maximum coins which can be collected is " + maximumCost(matrix));
    }
}
