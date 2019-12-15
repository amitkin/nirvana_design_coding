package com.mylearning.onlinetests;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/longest-consecutive-sequence/ - similar
//Time - O(1)
//Asked in Google Telephonic round on Aug 30
public class SequenceStatus {

    private Map<Integer, Integer> map;

    SequenceStatus() {
        map = new HashMap<>();
    }

    void send(int requestId) {
        if (!map.containsKey(requestId)) {
            int left = map.getOrDefault(requestId - 1, 0);
            int right = map.getOrDefault(requestId + 1, 0);
            // sum: length of the sequence requestId is in
            int sum = left + right + 1;
            map.put(requestId, sum);

            // extend the length to the boundary(s)
            // of the sequence
            // will do nothing if n has no neighbors
            map.put(requestId - left, sum);
            map.put(requestId + right, sum);
        }
    }

    int getStatus() {
        return map.getOrDefault(1, 0);
    }

    public static void main(String[] args) {
        SequenceStatus google = new SequenceStatus();
        google.send(4);
        google.send(2);
        System.out.println(google.getStatus()); //return 0
        google.send(1);
        System.out.println(google.getStatus()); //return 2
        google.send(3);
        System.out.println(google.getStatus()); //return 4
        google.send(100);
        System.out.println(google.getStatus()); //return 4
    }
}
