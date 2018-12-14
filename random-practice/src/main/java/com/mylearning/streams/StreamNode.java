package com.mylearning.streams;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class StreamNode {
    class Node{
        Integer value;
        Node id; //Parent id

        public Stream<Node> getChildren() {
            return Stream.of(id)
                    .filter(Objects::nonNull);
        }

    }

    Stream<Node> findSubTree(Stream<Node> tree, Node node){

        return null;
    }

    List<Node> getChildren(Node node){
        return Collections.emptyList();
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