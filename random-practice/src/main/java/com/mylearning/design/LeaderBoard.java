package com.mylearning.design;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
First, I gave the priorityQueue solution but the interviewer asked me to do some trade-off to improve the performance.
PriorityQueue removal is O(N) compared to TreeMap where it is O(logN)

1.Use HashMap to record the people's score
2.Use TreeMap to find the topK in O(klogn) by traverse the treemap
3.Reset we can just remove the key from the treemap which is O(log n), same for addScore().

You can also create Player class with id and score. Use HashMap<Integer, Player> and TreeSet<Player>
*/
class Leaderboard {
    Map<Integer, Integer> map; //key is palyerId, value is score
    TreeMap<Integer, Integer> sorted; //key is the score, value is the no. of players with that score

    public Leaderboard() {
        map = new HashMap<>();
        sorted = new TreeMap<>(Collections.reverseOrder());
    }

    public void addScore(int playerId, int score) {
        if (!map.containsKey(playerId)) {
            map.put(playerId, score);
            sorted.put(score, sorted.getOrDefault(score, 0) + 1);
        } else {
            int preScore = map.get(playerId);
            sorted.put(preScore, sorted.get(preScore) - 1);
            if (sorted.get(preScore) == 0) {
                sorted.remove(preScore);
            }
            int newScore = preScore + score;
            map.put(playerId, newScore);
            sorted.put(newScore, sorted.getOrDefault(newScore, 0) + 1);
        }
    }

    public void reset(int playerId) {
        int preScore = map.get(playerId);
        sorted.put(preScore, sorted.get(preScore) - 1);
        if (sorted.get(preScore) == 0) {
            sorted.remove(preScore);
        }
        map.remove(playerId);
    }

    //Return the score sum of the top K players
    public int top(int K) {
        int ans = 0;
        for (Map.Entry<Integer, Integer> e: sorted.entrySet()) {
            int score = e.getKey();
            int cnt = e.getValue();
            int n = Math.min(cnt, K);
            ans += n*score;
            K -= n;
            if (K == 0) break;
        }
        return ans;
    }

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard ();
        leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
        leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
        leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
        leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
        leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
        leaderboard.top(1);           // returns 73;
        leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
        leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
        leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
        leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
    }
}