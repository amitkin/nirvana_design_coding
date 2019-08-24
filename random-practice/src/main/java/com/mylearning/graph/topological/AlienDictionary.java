package com.mylearning.graph.topological;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//Time complexity is O(V+E), where ‘V’ is the total number of different characters and ‘E’ is the total number of the rules in the alien language.
//Since, at most, each pair of words can give us one rule, therefore, we can conclude that the upper bound for the rules is O(N) where ‘N’ is the number of words in the input.
//So, we can say that the time complexity of our algorithm is O(V+N) and also space complexity is O(V+N)
public class AlienDictionary {

    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegreeMap = new HashMap<>();
        /* MUST initialize the map, to avoid null exception for those character that will have zero inDegrees (i.e. starting characters) */
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegreeMap.put(c, 0);
            }
        }

        /* build graph, as well as fill out inDegree map for every character */
        for (int i = 0; i < words.length - 1; i++) {
            String curWord = words[i];
            String nextWord = words[i + 1];
            int minLength = Math.min(curWord.length(), nextWord.length());
            /* according to given dictionary with specified order, traverse every pair of words,
             * then put each pair into graph map to build the graph, and then update inDegree map
             * for every "nextChar" (increase their inDegree by 1 every time) */
            for (int j = 0; j < minLength; j++) {
                char curChar = curWord.charAt(j);
                char nextChar = nextWord.charAt(j);
                if (curChar != nextChar) {
                    /* update graph map */
                    graph.putIfAbsent(curChar, new HashSet<>());
                    Set<Character> set = graph.get(curChar);
                    /** WARNING: we must check if we already build curChar -> nextChar relationship in graph
                     * if it contains, we cannot update inDegree map again. Otherwise, this nextChar
                     * will never be put in the queue when we do BFS traversal
                     * eg: for the input: {"za", "zb", "ca", "cb"}, we have two pairs of a -> b relationship
                     * if we increase inDegree value of 'b' again, the final result will not have 'b', since
                     * inDegree of b will stay on 1 when queue is empty
                     * correct graph: a -> b, z -> c
                     * incorrect graph: a -> b, a -> b, z -> c
                     * */
                    if (!set.contains(nextChar)) {
                        set.add(nextChar);
                        graph.put(curChar, set);
                        /* update inDegree map */
                        inDegreeMap.put(nextChar, inDegreeMap.getOrDefault(nextChar, 0) + 1);
                    }
                    /* we can determine the order of characters only by first different pair of characters so we cannot add relationship by the rest of characters */
                    break;
                }
            }
        }
        /* after building graph, we will have an input that has exact same format as Course Schedule, then we can use BFS to do topological sort */
        StringBuilder sortedOrder = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        /* put all starting node into queue, which means put all nodes that have inDegree = 0 */
        for (char key : inDegreeMap.keySet()) {
            if (inDegreeMap.get(key) == 0) {
                queue.offer(key);
            }
        }
        /* BFS traversal to build result string */
        while (!queue.isEmpty()) {
            char curChar = queue.poll();
            sortedOrder.append(curChar);
            /* traverse all next node of current node in graph, update inDegree value then put all nodes with zero inDegree into queue */
            if (graph.containsKey(curChar)) {
                for (char nextChar : graph.get(curChar)) {
                    inDegreeMap.put(nextChar, inDegreeMap.get(nextChar) - 1);
                    if (inDegreeMap.get(nextChar) == 0) {
                        queue.offer(nextChar);
                    }
                }
            }
        }
        /* check if input order is valid */
        if (sortedOrder.length() != inDegreeMap.size()) {
            return "";
        }
        return sortedOrder.toString();
    }

    public static void main(String[] args) {
        String result = AlienDictionary.alienOrder(new String[] { "ba", "bc", "ac", "cab" });
        System.out.println("Character order: " + result);

        result = AlienDictionary.alienOrder(new String[] { "cab", "aaa", "aab" });
        System.out.println("Character order: " + result);

        result = AlienDictionary.alienOrder(new String[] { "ywx", "xww", "xz", "zyy", "zwz" });
        System.out.println("Character order: " + result);
    }
}
