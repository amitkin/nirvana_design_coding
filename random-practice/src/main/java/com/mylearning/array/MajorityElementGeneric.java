package com.mylearning.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MajorityElementGeneric {

    public List<Integer> searchFrequentItems(int[] nums, int k) {

        // Finds the candidates which may occur > n / k times.
        Map<Integer, Integer> table = new HashMap<>();
        int n = 0; // Counts the number of integers.

        for (int num : nums) {
            table.put(num, table.getOrDefault(num, 0) + 1);
            ++n;
            // Detecting k items in table, at least one of them must have exactly one
            // in it. We will discard those k items by one for each.
            if (table.size() == k) {
                List<Integer> delKeys = new ArrayList<>();
                for (Map.Entry<Integer, Integer> entry : table.entrySet()) {
                    if (entry.getValue() - 1 == 0) {
                        delKeys.add(entry.getKey());
                    } else {
                        table.put(entry.getKey(), entry.getValue() - 1);
                    }
                }
                for (Integer s : delKeys) {
                    table.remove(s);
                }
            }
        }

        // Resets table for the following counting.
        table = table.entrySet().stream().collect(
                Collectors.toMap(e -> e.getKey(), e -> 0));

        // Counts the occurrence of each candidate number.
        for (int num : nums) {
            Integer count = table.get(num);
            if (count != null) {
                table.put(num, count + 1);
            }
        }

        final double threshold = (double)n / k;
        // Selects the number which occurs > n / k times.
        return table.entrySet()
                .stream()
                .filter(it -> threshold < (double)it.getValue())
                .map(it -> it.getKey())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        MajorityElementGeneric m = new MajorityElementGeneric();
        int[] arr = {1,1,1,3,3,2,2,2};
        System.out.println(m.searchFrequentItems(arr, 3));
    }
}
