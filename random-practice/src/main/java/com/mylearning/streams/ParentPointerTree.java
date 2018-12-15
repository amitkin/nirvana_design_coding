package com.mylearning.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Parent Pointer Tree
// https://en.wikipedia.org/wiki/Parent_pointer_tree
public class ParentPointerTree {
    static class Node{
        Integer id;
        Node parent;

        public Node(Integer id, Node parent) {
            this.id = id;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", parent=" + parent +
                    '}';
        }
    }

    private Stream<Node> findSubTree(Stream<Node> tree, Node node){
        //Create a map of <parent, List<children>
        Map<Integer, List<Node>> parentChildrenMap = new HashMap<>();
        tree.forEach(n -> {
            if(n.parent == null){
                parentChildrenMap.put(null, Collections.singletonList(n));
            } else {
                List<Node> childNodes = parentChildrenMap.getOrDefault(n.parent.id, new ArrayList<>());
                childNodes.add(n);
                parentChildrenMap.put(n.parent.id, childNodes);
            }
        });

        List<Node> unvisitedNodes = new LinkedList<>();
        List<Node> visitedNodes = new LinkedList<>();
        unvisitedNodes.add(node);
        while(!unvisitedNodes.isEmpty()) {
            Node currNode = unvisitedNodes.remove(0);
            List<Node> newNodes = parentChildrenMap.getOrDefault(currNode.id, Collections.emptyList())
                    .stream()
                    .filter(n -> n!=null && !visitedNodes.contains(n))
                    .collect(Collectors.toList());
            if(newNodes.size() > 0) {
                unvisitedNodes.addAll(0, newNodes);
            }
            visitedNodes.add(currNode);
        }
        return visitedNodes.stream();
    }

    public static void main(String[] args) {
        Node root = new Node(50, null);
        Node n1 = new Node(30, root);
        Node n2 = new Node(70, root);
        Node n3 = new Node(20, n1);
        Node n4 = new Node(40, n1);
        Node n5 = new Node(60, n2);
        Node n6 = new Node(80, n2);

        ParentPointerTree s = new ParentPointerTree();
        Stream<Node> resultStream = s.findSubTree(Stream.of(root, n1, n2, n3, n4, n5, n6), n1);
        resultStream.forEach(System.out::println);
    }
}

/* Let us create following BST
          50
       /     \
      30      70
     /  \    /  \
   20   40  60   80


50 null
30 50
70 50
20 30
40 30
60 70
80 70
*/