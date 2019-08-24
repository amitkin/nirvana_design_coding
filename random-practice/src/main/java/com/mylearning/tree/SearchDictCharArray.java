package com.mylearning.tree;

// Java program to print all valid words that are possible using character of array
// Asked in Microsoft
// Time complexity would be O( n + m*k) n being the time it takes for you to form your hash table, m being the length of your words array,
// k being the time for you to scan the entirety of each word.
class SearchDictCharArray {
    // Alphabet size
    static final int SIZE = 26;

    // trie Node
    static class TrieNode {
        TrieNode[] child = new TrieNode[SIZE];

        // isLeaf is true if the node represents
        // end of a word
        boolean leaf;

        // Constructor
        public TrieNode() {
            leaf = false;
            for (int i =0 ; i< SIZE ; i++)
                child[i] = null;
        }
    }

    // If not present, inserts key into trie
    // If the key is prefix of trie node, just
    // marks leaf node
    static void insert(TrieNode root, String Key) {
        int n = Key.length();
        TrieNode pChild = root;

        for (int i=0; i<n; i++)
        {
            int index = Key.charAt(i) - 'a';

            if (pChild.child[index] == null)
                pChild.child[index] = new TrieNode();

            pChild = pChild.child[index];
        }

        // make last node as leaf node
        pChild.leaf = true;
    }

    // A recursive function to print all possible valid
    // words present in array
    static void searchWord(TrieNode root, boolean hash[], String str) {
        // if we found word in trie / dictionary
        if (root.leaf == true)
            System.out.println(str);

        // traverse all child's of current root
        for (int k =0; k < SIZE; k++)
        {
            if (hash[k] == true && root.child[k] != null )
            {
                // add current character
                char c = (char) (k + 'a');

                // Recursively search reaming character
                // of word in trie
                searchWord(root.child[k], hash, str + c);
            }
        }
    }

    // Prints all words present in dictionary.
    static void PrintAllWords(char arr[], TrieNode root) {
        // create a 'has' array that will store all
        // present character in Arr[]
        boolean[] hash = new boolean[SIZE];

        for (int i = 0 ; i < arr.length; i++)
            hash[arr[i] - 'a'] = true;

        // temporary node
        TrieNode pChild = root ;

        // string to hold output words
        String str = "";

        // Traverse all matrix elements. There are only
        // 26 character possible in char array
        for (int i = 0 ; i < SIZE ; i++) {
            // we start searching for word in dictionary
            // if we found a character which is child
            // of Trie root
            if (hash[i] && pChild.child[i] != null ) {
                str = str+(char)(i + 'a');
                searchWord(pChild.child[i], hash, str);
                str = "";
            }
        }
    }

    //Driver program to test above function
    public static void main(String args[]) {
        // Let the given dictionary be following
        String dict[] = {"go", "bat", "me", "eat", "goal", "boy", "run"} ;

        // Root Node of Trie
        TrieNode root = new TrieNode();

        // insert all words of dictionary into trie
        int n = dict.length;
        for (int i=0; i<n; i++)
            insert(root, dict[i]);

        char arr[] = {'e', 'o', 'b', 'a', 'm', 'g', 'l'} ;
        PrintAllWords(arr, root);
    }
}

