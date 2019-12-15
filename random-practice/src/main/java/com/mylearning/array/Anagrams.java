package com.mylearning.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagrams {
    public static List<List<String>> findAnagrams(List<String> dictionary) {

        Map<String, List<String>> sortedStringToAnagrams = new HashMap<>();
        for (String s : dictionary) {
            // Sorts the string, uses it as a key, and then appends
            // the original string as another value in the hash table.
            String sortedStr = Stream.of(s.split("")).sorted().collect(Collectors.joining());
            sortedStringToAnagrams.putIfAbsent(sortedStr, new ArrayList<>());
            sortedStringToAnagrams.get(sortedStr).add(s);
        }

        return sortedStringToAnagrams.values()
                .stream()
                .filter(group -> group.size() >= 2)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams(Arrays.asList("debitcard", "elvis", "silent", "badcredit", "lives",
                "freedom", "listen", "levis", "money")));
    }
}
