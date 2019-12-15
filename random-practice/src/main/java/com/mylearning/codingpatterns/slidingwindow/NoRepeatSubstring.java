package com.mylearning.codingpatterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem
public class NoRepeatSubstring {
    public static int findLength(String str) {
        // TODO: Write your code here
        Map<Character, Integer> map = new HashMap<>();
        int begin = 0, end = 0, counter = 0, d = 0;

        while (end < str.length()) {
            // > 0 means repeating character
            //if(map[str.charAt(end++)]-- > 0) counter++;
            char c = str.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(map.get(c) > 1) counter++;
            end++;

            while (counter > 0) {
                //if (map[str.charAt(begin++)]-- > 1) counter--;
                char charTemp = str.charAt(begin);
                if (map.get(charTemp) > 1) counter--;
                map.put(charTemp, map.get(charTemp)-1);
                begin++;
            }
            d = Math.max(d, end - begin);
        }
        return d;
    }

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("aabccbb"));
        System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("abbbb"));
        System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("abccde"));
    }
}
