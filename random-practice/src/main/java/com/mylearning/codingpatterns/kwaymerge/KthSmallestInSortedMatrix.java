package com.mylearning.codingpatterns.kwaymerge;

public class KthSmallestInSortedMatrix {

    /*
    1. Start the Binary Search with start = matrix[0][0] and end = matrix[n-1][n-1] as search space
    2. Find middle of the start and the end. This middle number is NOT necessarily an element in the matrix.
    3. Count all the numbers smaller than or equal to middle in the matrix. As the matrix is sorted, we can do this in O(N).
    4. While counting, we can keep track of the “smallest number greater than the middle” (let’s call it n1) and at the same time
    the “biggest number less than or equal to the middle” (let’s call it n2). These two numbers will be used to adjust the
    “number range” for the Binary Search in the next iteration.
    5. If the count is equal to ‘K’, n1 will be our required number as it is the “biggest number less than or equal to the middle”,
    and is definitely present in the matrix.
    6. If the count is less than ‘K’, we can update start = n2 to search in the higher part of the matrix and if the count is
    greater than ‘K’, we can update end = n1 to search in the lower part of the matrix in the next iteration.

    Time - O(log(max−min)) iterations where ‘max’ is the largest and ‘min’ is the smallest element in the matrix and in each
    iteration  we take O(N) for counting, therefore, the overall time complexity of the algorithm will be O(N∗log(max−min)).
    Space - O(1)
    */
    public static int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0,  j = matrix[0].length - 1;
            for(int i = 0; i < matrix.length; i++) {
                while(j >= 0 && matrix[i][j] > mid) j--;
                count += (j + 1);
            }
            if(count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    //Heap
    /*Build a minHeap of elements from the first row.
    Do the following operations k-1 times :
    Every time when you poll out the root(Top Element in Heap), you need to know the row number and
    column number of that element(so we can create a tuple class here), replace that root with the
    next element from the same column.*/
    //The time complexity is O(k * log n), so the worst-case and average-case time complexity is O(n^2 * log n).
    //Space complexity is O(n).
    /*class Tuple implements Comparable<Tuple> {
        int x, y, val;
        public Tuple (int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo (Tuple that) {
            return this.val - that.val;
        }
    }
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        for(int j = 0; j < n; j++) {
            pq.offer(new Tuple(0, j, matrix[0][j]));
        }
        for(int i = 0; i < k-1; i++) {
            Tuple t = pq.poll();
            if(t.x == n-1) continue;
            pq.offer(new Tuple(t.x+1, t.y, matrix[t.x+1][t.y]));
        }
        return pq.poll().val;
    }*/

    public static void main(String[] args) {
        int matrix[][] = { { 1, 4 }, { 2, 5 } };
        int result = KthSmallestInSortedMatrix.kthSmallest(matrix, 2);
        System.out.println("Kth smallest number is: " + result);

        int matrix1[][] = { { -5 } };
        result = KthSmallestInSortedMatrix.kthSmallest(matrix1, 1);
        System.out.println("Kth smallest number is: " + result);

        int matrix2[][] = { { 2, 6, 8 }, { 3, 7, 10 }, { 5, 8, 11 } };
        result = KthSmallestInSortedMatrix.kthSmallest(matrix2, 5);
        System.out.println("Kth smallest number is: " + result);

        int matrix3[][] = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
        result = KthSmallestInSortedMatrix.kthSmallest(matrix3, 8);
        System.out.println("Kth smallest number is: " + result);

    }
}
