package com.mylearning.codingpatterns.topk;

import java.util.*;

public class RearrangeString {

    // Time complexity - O(N∗logN) where ‘N’ is the number of characters in the input string.
    // Space - O(N), as in the worst case, we need to store all the ‘N’ characters in the HashMap.
    // We can follow a greedy approach to find an arrangement of the given string where no two same characters come next to each other.
    // We can work in a stepwise fashion to create a string with all characters from the input string. We should first
    // append the most frequent characters to the output strings, for which we can use a Max Heap. In each step, we
    // should append one occurrence of the highest frequency character to the output string without putting the
    // character back in the heap to ensure that no two same characters are adjacent to each other. In the next step,
    // we should process the next most frequent character in the same way and then, at the end of this step, insert
    // the character from the previous step back to the heap after decrementing its frequency.
    // Following this algorithm, if we can append all the characters from the input string to the output string, we
    // would have successfully found an arrangement of the given string where no two same characters appeared adjacent to each other.
    public static String rearrangeString(String str) {
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : str.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<Map.Entry<Character, Integer>>(
                (e1, e2) -> e2.getValue() - e1.getValue());

        // add all characters to the max heap
        maxHeap.addAll(charFrequencyMap.entrySet());

        Map.Entry<Character, Integer> previousEntry = null;
        StringBuilder resultString = new StringBuilder(str.length());
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
            // add the previous entry back in the heap if its frequency is greater than zero
            if (previousEntry != null && previousEntry.getValue() > 0) {
                maxHeap.offer(previousEntry);
            }
            // append the current character to the result string and decrement its count
            resultString.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue() - 1);
            previousEntry = currentEntry;
        }

        // if we were successful in appending all the characters to the result string, return it
        return resultString.length() == str.length() ? resultString.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println("Rearranged string: " + RearrangeString.rearrangeString("aappp"));
        System.out.println("Rearranged string: " + RearrangeString.rearrangeString("Programming"));
        System.out.println("Rearranged string: " + RearrangeString.rearrangeString("aapa"));
    }
}

