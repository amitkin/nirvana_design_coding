package com.mylearning.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadderII {
    /*
    The basic idea is:
    1). Use BFS to find the shortest distance between start and end, tracing the distance of crossing nodes from start node
    to end node, and store node's next level neighbors to HashMap; in BFS , we can be sure that the distance of each node
    is the shortest one , because once we have visited a node, we update the distance , if we first met one node ,it must
    be the shortest distance. if we met the node again ,its distance must not be less than the distance we first met and set.

    2). Use DFS to output paths with the same distance as the shortest distance from distance HashMap: compare if the
    distance of the next level node equals the distance of the current node + 1.
    */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if(wordList==null || wordList.isEmpty())
            return res;

        Set<String> wordDict = new HashSet<>(wordList);
        if (!wordDict.contains(endWord)){
            return res;
        }

        Map<String,Set<String>> neighbours =new HashMap<>();

        bfs(beginWord, endWord, wordDict, neighbours);

        dfs(beginWord, endWord, wordDict, neighbours, new ArrayList<>(), res);
        return res;
    }

    private void bfs(String beginWord, String endWord, Set<String> wordDict, Map<String,Set<String>> neighbours){
        Map<String,Integer> distance = new HashMap<>();
        Queue<String> toVisit = new LinkedList<>();
        toVisit.offer(beginWord);
        distance.put(beginWord, 1);
        boolean foundIt = false;

        while (!toVisit.isEmpty()) {
            int num = toVisit.size();
            for (int i = 0; i < num; i++) {
                String word = toVisit.poll();
                //Just populating neighbours without much logic
                Set<String> curneighbours = getNextWords(word, wordDict);
                Iterator<String> iterator= curneighbours.iterator();

                //curneighbours are updated by removing not shortest ones
                for (; iterator.hasNext();) {
                    String neighbour = iterator.next();
                    if(word.equals(endWord)){
                        foundIt=true;
                    }

                    //Important to note, we are using distance map for tracking visited
                    if (!distance.containsKey(neighbour)) { // not visited
                        distance.put(neighbour, distance.get(word) + 1);
                        //since we need all paths so put all valid shortest neighbours in queue
                        toVisit.offer(neighbour);
                    } else { // already visited
                        if( distance.get(neighbour) != distance.get(word)+1)//if not shortest
                            iterator.remove(); //remove since not shortest
                    }
                }

                //neighbours map contains only shortest neighbours
                neighbours.put(word, curneighbours);
            }

            //since we need all the shortest transformations, so breaking once we see endword
            //It will also include multiple transformations at this level since we are
            //processing all neighbours at this level
            if(foundIt)
                break;
        }
    }

    //populate neighbours
    private Set<String> getNextWords(String word, Set<String> wordDict){
        Set<String> res = new HashSet<>();

        for (int i = 0; i < word.length(); i++) {
            for (char letter = 'a'; letter <= 'z'; letter++) {
                StringBuilder newWord = new StringBuilder(word);
                newWord.setCharAt(i, letter);
                //excluding itself by checking to avoid self loop
                if (!newWord.equals(word) && wordDict.contains(newWord.toString())) {
                    res.add(newWord.toString());
                }
            }
        }
        return res;
    }

    // Backtracking DFS: output all paths with the shortest distance
    private void dfs(String curword, String end, Set<String> dict, Map<String,Set<String>> neighbours, List<String> worklist, List<List<String>> res){

        if(curword.equals(end)){
            worklist.add(curword);
            res.add(new ArrayList<>(worklist));
            worklist.remove(worklist.size()-1);
            return;
        }
        worklist.add(curword);
        if(neighbours.containsKey(curword)) {
            for (String nb : neighbours.get(curword)) {
                dfs(nb, end, dict, neighbours, worklist, res);
            }
        }
        worklist.remove(worklist.size()-1);
    }

    public static void main(String[] args) {
        WordLadderII wordLadder = new WordLadderII();
        System.out.println(wordLadder.findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
    }
}
