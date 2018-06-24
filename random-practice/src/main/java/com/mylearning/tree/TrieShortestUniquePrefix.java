package com.mylearning.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//https://www.interviewbit.com/problems/shortest-unique-prefix/
public class TrieShortestUniquePrefix {

    class TrieNode{
        Map<Character, TrieNode> children;
        boolean endOfWord;
        int wordCount;

        TrieNode(){
            children = new HashMap<>();
            endOfWord = false;
            wordCount = 0;
        }
    }

    private final TrieNode root;

    public TrieShortestUniquePrefix(){
        root = new TrieNode();
    }


    public void insert(String word){
        TrieNode current = root;
        for(int i=0; i<word.length(); i++){
            current.wordCount += 1;
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null){
                node = new TrieNode();
                current.children.put(ch, node);
            }

            current = node;
        }

        current.endOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            //if node does not exist for given char then return false
            if (node == null) {
                return false;
            }
            current = node;
        }
        //return true of current's endOfWord is true else return false.
        return current.endOfWord;
    }

    public int prefixSearch(String prefix){
        TrieNode current = root;
        for(int  i=0; i< prefix.length(); i++){
            char ch = prefix.charAt(i);
            if(!current.children.containsKey(ch)){
                return 0;
            }else{
                current = current.children.get(ch);
            }
        }

        return current.wordCount;
    }


    public static void main(String [] args){
        TrieShortestUniquePrefix test = new TrieShortestUniquePrefix();
        ArrayList<String> output = new ArrayList<>();
        String [] inputs = new String[]{"zebra","dog","duck","dove"};
        for(String str: inputs){
            test.insert(str);
        }

        for(String str: inputs){
            String search = "";
            for(char ch: str.toCharArray()){
                search = search+ch;
                int res = test.prefixSearch(search);

                if(res == 1)
                    break;
            }
            output.add(search);
        }

        System.out.println(output.toString());
    }
}
