package com.mylearning.array;

import java.util.Random;

//https://leetcode.com/discuss/interview-question/algorithms/124759/interview-question-randomly-generate-mines-on-a-grid
public class GenerateKMines {

    private Random random;
    GenerateKMines() {
        random = new Random();
    }

    //Runtime is O(k)
    //Probability is [k/(m*n)-k]
    /*
    Use reservoir sampling: k out of mn.
    To make it easier to understand, assign an element ID to each grid square 1..mn.
    To begin the algo, first k IDs are added to the reservoir of k elements.
    Subsequently, go through the rest of the elements (k+1 .. m*n) and generate a random number (r) between 1 and the element ID in the iteration.
    If the random number (r) is between 1 and k, replace the rth value in the reservoir with the new element ID.
    At the end, the reservoir list will have the element IDs of the grid which need to be mined. So convert these back to the grid indices.
    */
    private void putMines(char[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalSize = m*n;

        for (int i = totalSize - k; i<totalSize; i++) {

            int randomIndex = random.nextInt(i+1);

            int m1 = randomIndex%m;
            int n1 = randomIndex%n;

            if (grid[m1][n1] == 'X'){
                grid[i%m][i%n] = 'X';
            }else{
                grid[m1][n1] = 'X';
            }
        }
    }

    public static void main(String[] args) {
        char grid[][]=new char[][]{{'-','-','-','-'},{'-','-','-','-'},{'-','-','-','-'}};
        new GenerateKMines().putMines(grid,3);
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }
}
