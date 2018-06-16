package com.mylearning.epi.tree;

import com.mylearning.epi.test_framework.EpiTest;
import com.mylearning.epi.test_framework.GenericTest;
import com.mylearning.epi.tree.BinaryTreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TreePreorder {
  @EpiTest(testDataFile = "tree_preorder.tsv")

  public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {

    Deque<BinaryTreeNode<Integer>> path = new ArrayDeque<>();
    if (tree != null) {
      path.addFirst(tree);
    }
    List<Integer> result = new ArrayList<>();
    while (!path.isEmpty()) {
      BinaryTreeNode<Integer> curr = path.removeFirst();
      result.add(curr.data);
      if (curr.right != null) {
        path.addFirst(curr.right);
      }
      if (curr.left != null) {
        path.addFirst(curr.left);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreePreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
