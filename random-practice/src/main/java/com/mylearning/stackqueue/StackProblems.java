package com.mylearning.stackqueue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.mylearning.tree.BinarySearchTree.BinaryTreeNode;

public class StackProblems {

    public static boolean isWellFormed(String s) {
        Deque<Character> leftChars = new LinkedList<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                leftChars.addFirst(s.charAt(i));
            } else {
                if (leftChars.isEmpty()) {
                    return false; // Unmatched right char.
                }
                if ((s.charAt(i) == ')' && leftChars.peekFirst() != '(') || (s.charAt(i) == '}'
                        && leftChars.peekFirst() != '{') || (s.charAt(i) == ']' && leftChars.peekFirst() != '[')) {
                    return false; // Mismatched chars.
                }
                leftChars.removeFirst();
            }
        }
        return leftChars.isEmpty();
    }

    public static String shortestEquivalentPath(String path) {
        if (path.equals("")) {
            throw new IllegalArgumentException("Empty string is not a legal path.");
        }
        Deque<String> pathNames = new LinkedList<>();
        // Special case: starts with which is an absolute path.
        if (path.startsWith("/")) {
            pathNames.addFirst("/");
        }
        for (String token : path.split("/")) {
            if (token.equals("..")) {
                if (pathNames.isEmpty() || pathNames.peekFirst().equals("..")) {
                    pathNames.addFirst(token);
                } else {
                    if (pathNames.peekFirst().equals("/")) {
                        throw new IllegalArgumentException(
                                "Path error, trying to go up root " + path);
                    }
                    pathNames.removeFirst();
                }
            } else if (!token.equals(".") && !token.isEmpty()) { // Must be a name.
                pathNames.addFirst(token);
            }
        }
        StringBuilder result = new StringBuilder();
        if (!pathNames.isEmpty()) {
            Iterator<String> it = pathNames.descendingIterator();
            String prev = it.next();
            result.append(prev);
            while (it.hasNext()) {
                if (!prev.equals("/")) {
                    result.append("/");
                }
                prev = it.next();
                result.append(prev);
            }
        }
        return result.toString();
    }

    public static void setJumpOrderRecursive(PostingListNode L){
        setJumpOrderHelper(L, 0);
    }

    private static int setJumpOrderHelper(PostingListNode L, int order) {
        if (L != null && L.order == -1) {
            L.order = order++;
            order = setJumpOrderHelper(L.jump, order);
            order = setJumpOrderHelper(L.next, order);
        }
        return order;
    }

    //Above recursion using stackqueue
    public static void setJumpOrder(PostingListNode L){
        Deque<PostingListNode> s = new LinkedList<>();
        int order = 0;
        s.addFirst(L);
        while(!s.isEmpty()){
            PostingListNode curr = s.removeFirst();
            //order is initialized to -1 for all the nodes
            if (curr != null && curr.order == -1) {
                curr.order = order++;
                // Stack is last-in, first -out, and we want to process
                // the jump node first, so push next, then push jump.
                s.addFirst(curr.next);
                s.addFirst(curr.jump);
            }
        }
    }

    class PostingListNode {
        int data;
        int order;
        PostingListNode jump;
        PostingListNode next;

        private PostingListNode(int data, int order) {
            this.data = data;
            this.order = order;
            jump = null;
            next = null;
        }
    }

    private static class BuildingWithHeight {
        public Integer id;
        public Integer height;

        public BuildingWithHeight(Integer id, Integer height) {
            this.id = id;
            this.height = height;
        }
    }

    //Buildings are given in east-to-west order
    //Specifically, we use a stackqueue to record buildings that have a view. Each time a
    //building b is processed, if it is taller than the building at the top of the stackqueue, we pop
    //the stackqueue until the top of the stackqueue is taller than b—all the buildings thus removed lie
    //to the east of a taller building.
    public static Deque<BuildingWithHeight> examineBuildingsWithSunset(Iterator<Integer> sequence) {
        int buildingldx = 0;
        Deque<BuildingWithHeight> buildingsWithSunset = new LinkedList<>();
        while (sequence.hasNext()) {
            Integer buildingHeight = sequence.next();
            while (!buildingsWithSunset.isEmpty() && (Integer.compare(buildingHeight, buildingsWithSunset.getLast().height) >= 0)) {
                buildingsWithSunset.removeLast();
            }
            buildingsWithSunset.addLast(new BuildingWithHeight(buildingldx++, buildingHeight));
        }
        return buildingsWithSunset;
    }

    //In the following, we use a queue of nodes to store nodes at depth i and a queue
    //of nodes at depth i + 1. After all nodes at depth i are processed, we are done with
    //that queue, and can start processing the queue with nodes at depth i +1, putting the
    //depth i+ 2 nodes in a new queue.
    public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode tree) {
        Queue<BinaryTreeNode> currDepthNodes = new LinkedList<>();
        currDepthNodes.add(tree);
        List<List<Integer>> result = new ArrayList<>();
        while (!currDepthNodes.isEmpty()){
            Queue<BinaryTreeNode> nextDepthNodes = new LinkedList<>();
            List<Integer> thisLevel = new ArrayList<>();
            while (!currDepthNodes.isEmpty()){
                BinaryTreeNode curr = currDepthNodes.poll();
                if (curr != null) {
                    thisLevel.add(curr.data);
                    // Defer the null checks to the null test above.
                    nextDepthNodes.add(curr.left);
                    nextDepthNodes.add(curr.right);
                }
            }
            if (!thisLevel.isEmpty()){
                result.add(thisLevel);
            }
            currDepthNodes = nextDepthNodes;
        }
        return result ;
    }

    public static void main(String[] args) {
        String parenthesis = "[()[]|()()|]";
        System.out.println(isWellFormed(parenthesis));

        String path = "sc//./../tc/awk/././";
        System.out.println(shortestEquivalentPath(path));
    }
}
