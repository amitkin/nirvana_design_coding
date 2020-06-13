package com.mylearning.graph;

class LargestBoxes{

    //https://leetcode.com/problems/max-area-of-island/
    //Given a rectangular map of a warehouse (represented as char[][]) with box and floor tiles, return the largest group of boxes on that map

    // B..
    // B.. =4
    // BB.

    // BBB..
    // B.B..
    // .B.BB =5
    // B..BB
    // .B...

    //...B.
    //...B. =4
    //..BB.

    //B..B
    //B..B =6
    //B..B

    int maxSofar = 0;
    int maxCurrent = 0;
    int getMaximumConnectedBoxes(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        if(matrix[0].length == 0) return 0;

        int row = matrix.length;
        int col = matrix[0].length;

        boolean[][] visited = new boolean[row][col];
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                //traverse for each starting point
                if(matrix[i][j] == 'B') {
                    traverse(matrix, i, j, visited);
                    maxSofar = Math.max(maxSofar, maxCurrent);
                    maxCurrent = 0;
                }
            }
        }
        return maxSofar;
    }

    void traverse(char[][] matrix, int i, int j, boolean[][] visited) {
        //validation if within matrix
        if(i>=0 && i<matrix.length && j>=0 && j< matrix[0].length && matrix[i][j] == 'B' && !visited[i][j]) {
            //traverse all the directions
            visited[i][j] = true;
            maxCurrent++;
            traverse(matrix, i+1, j, visited);
            traverse(matrix, i-1, j, visited);
            traverse(matrix, i, j+1, visited);
            traverse(matrix, i, j-1, visited);
        }
        return;
    }

    public static void main(String[] args) {
        LargestBoxes l = new LargestBoxes();
        //char[][] matrix = {{'B','B', 'B', ' ', ' '},{'B',' ', 'B', ' ', ' '}, {' ','B', ' ', 'B', 'B'}, {'B','B', 'B', ' ', ' '}, {' ','B', ' ', ' ', ' '}};
        char[][] matrix = {{'B',' ', 'B'},{'B',' ', 'B'}, {'B',' ','B'}};
        System.out.println(l.getMaximumConnectedBoxes(matrix));
    }
}




