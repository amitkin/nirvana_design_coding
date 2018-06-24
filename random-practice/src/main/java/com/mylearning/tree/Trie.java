package com.mylearning.tree;

import java.util.*;

public class Trie{

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
    public Trie(){
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
        Trie test = new Trie();
        test.insert("abc");
        test.insert("amd");
        test.insert("pqr");
        test.insert("@345");

        System.out.println(test.search("@345"));
        System.out.println(test.prefixSearch("a"));
    }
}
