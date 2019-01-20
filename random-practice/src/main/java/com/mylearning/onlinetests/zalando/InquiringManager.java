package com.mylearning.onlinetests.zalando;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

import javafx.util.Pair;

/*
https://www.hackerrank.com/contests/zalando-codesprint/challenges/the-inquiring-manager
Input:
11
1 150 0
1 3 10
2 40
1 143 59
2 59
1 100 60
2 60
1 159 61
2 61
2 120
2 121

Output:
150
150
143
159
159
-1
 */
public class InquiringManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); //range is pow(10,6)

        Deque<Pair<Long, Long>> queue = new LinkedList<>();

        /*PriorityQueue<Pair<Long, Long>> queue = new PriorityQueue<>((p1, p2) -> {
            if (p1.getKey() > p2.getKey()) {
                return -1;
            } else if (p1.getKey().equals(p2.getKey())) {
                return 0;
            } else {
                return 1;
            }
        });*/

        int type;
        long p, t; //range is pow(10,18)
        for (int i = 0; i < n; i++) {
            type = scanner.nextInt();
            if (type == 1) {
                p = scanner.nextLong();
                t = scanner.nextLong();
                Pair<Long, Long> entry = new Pair<>(p, t);

                while(!queue.isEmpty() && queue.peekLast().getKey() < entry.getKey()){
                    queue.removeLast();
                }
                queue.offer(entry);
            } else {
                t = scanner.nextLong();
                while (!queue.isEmpty()) {
                    Pair cur = queue.peekFirst();
                    if (Long.valueOf(cur.getValue().toString()) <= t - 60) {
                        queue.poll();
                    } else {
                        break;
                    }
                }
                if (queue.isEmpty()) {
                    System.out.println("-1");
                } else {
                    System.out.println(queue.peek().getKey());
                }
            }
        }
    }
}
