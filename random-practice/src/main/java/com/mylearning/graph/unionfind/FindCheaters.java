package com.mylearning.graph.unionfind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/*
You have been hired as TA of Python, 101. Students have turned in their first assignments, but unfortunately there has been a lot of plagiarism.
Your college provides a service that runs against all assignments and gives you pairs of assignments that appear to have been copied.
Your job as TA, is to identify groups of people who copied from each other or from the same source and call them for questioning:

Input
Any number of lines having pairs of input representing roll number of the student who copied from the next student. For eg.
305641, 305581
305641, 305051
305051, 305581
305051, 305021
305021, 305051
306532, 305111
306532, 205121
305641, 205874
305532, 305182

Output:
205121, 305111, 306532
205874, 305021, 305051, 305581, 305641
305182, 305532

Note: The group of students who cheated should have roll numbers in sorted order as shown above.

Explanation:

Find minimum roll number in the list (since we want to go in ascending order): 205121
See who copied from this student or whom this student copied from : 205121 was copied by 306532 and 305632 copied from 305111. This sequence ends here since 305111 did not copy from anyone.
Therefore, take those three roll numbers in ascending order and add them to answer. Hence the first row 205121, 305111, 306532
Similarly, go to the next roll number in the remaining list i.e. 205874 and do 2nd step onwards ..
*/

// complexity
//n - total no of userIds on the report
//space - n
//time - n*logn

// It has many connected components so preferring union find
// It has a cyclic so no topological sort possible, also they are expecting a sorted list
// If going for DFS, need to cover all elements and also have visited map.
// can be done recursively and iteratively
// there is a way to improve union, need to check on that.
public class FindCheaters {

    private List<List<Integer>> findCheaters(int[][] report) {
        List<List<Integer>> result = new ArrayList<>();
        if(report == null)
            return result;
        Map<Integer, Integer> parentMap = new HashMap<>();
        Map<Integer, TreeSet<Integer>> group = new HashMap<>();

        // initializing the parent as self
        for(int[] userIds: report) {
            parentMap.putIfAbsent(userIds[0],userIds[0]);
            parentMap.putIfAbsent(userIds[1],userIds[1]);
        }

        // finding the right parent
        for(int[] userIds : report) {
            int x = getParent(parentMap, userIds[0]);
            int y = getParent(parentMap, userIds[1]);
            parentMap.put(x, y);
        }

        // preparing the group
        for(int[] userIds : report) {
            int parent = getParent(parentMap, userIds[1]);
            group.putIfAbsent(parent, new TreeSet<>());
            group.get(parent).add(userIds[0]);
            group.get(parent).add(userIds[1]);
        }

        // adding to the result
        for(Map.Entry<Integer, TreeSet<Integer>> e : group.entrySet()) {
            List<Integer> list = new ArrayList<>(e.getValue());
            result.add(list);
        }

        // arranging the result
        Collections.sort(result, new Comparator<List<Integer>>(){
            public int compare(List<Integer> a, List<Integer> b) {
                return a.get(0).compareTo(b.get(0));
            }
        });
        return result;
    }

    private int getParent(Map<Integer, Integer> parentMap, int child) {
        while(parentMap.get(child) != child) {
            child = parentMap.get(child);
        }
        return child;
    }

    public static void main(String[] args) {
        int[][] report = {
                {305641, 305581},
                {305641, 305051},
                {305051, 305581},
                {305051, 305021},
                {305021, 305051},
                {306532, 305111},
                {306532, 205121},
                {305641, 205874},
                {305532, 305182}
        };
        List<List<Integer>> result = new FindCheaters().findCheaters(report);
        for(List<Integer> list : result) {
            for(Integer userid : list) {
                System.out.print(userid + " ");
            }
            System.out.println();
        }
    }
}
