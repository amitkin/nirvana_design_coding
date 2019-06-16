package com.mylearning.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordDict = new HashSet<>(wordList);
        if (!wordDict.contains(endWord)){
            return 0;
        }
        Queue<String> toVisit = new LinkedList<>();
        toVisit.offer(beginWord);
        int dist = 1;
        while (!toVisit.isEmpty()) {
            int num = toVisit.size();
            for (int i = 0; i < num; i++) {
                String word = toVisit.poll();
                if (word.equals(endWord)) return dist;
                addNextWords(word, wordDict, toVisit);
            }
            dist++;
        }
        return 0;
    }

    //Traversing neighbours for word as begin
    //Its not dfs/backtrack so that you need to add it back
    private void addNextWords(String word, Set<String> wordDict, Queue<String> toVisit){
        wordDict.remove(word);
        for (int i = 0; i < word.length(); i++) {
            for (char letter = 'a'; letter <= 'z'; letter++) {
                StringBuilder newWord = new StringBuilder(word);
                newWord.setCharAt(i, letter);
                //No need of !newWord.equals(word) since we update wordDict between calls to addNextWords
                //Once a valid word is removed from dict and put in queue that means it should give us shortest path
                //hit-> hot-> dot-> dog-> cog, shorter
                //hit-> hot-> dot-> hot-> dot-> dog-> cog, not shorter
                //If hot is not leading to end word in first path then even for second one it will not lead
                if (wordDict.contains(newWord.toString())) {
                    wordDict.remove(newWord.toString());
                    toVisit.offer(newWord.toString());
                }
            }
        }
    }

    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        System.out.println(wordLadder.ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
    }
}
