package com.mylearning.epi.tree;

import com.mylearning.epi.test_framework.EpiTest;
import com.mylearning.epi.test_framework.GenericTest;
import com.mylearning.epi.tree.BinaryTreeNode;

public class PathSum {
  @EpiTest(testDataFile = "path_sum.tsv")

  public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                   int remainingWeight) {

    if (tree == null) {
      return false;
    } else if (tree.left == null && tree.right == null) { // Leaf.
      return remainingWeight == tree.data;
    }
    // Non-leaf.
    return hasPathSum(tree.left, remainingWeight - tree.data) ||
        hasPathSum(tree.right, remainingWeight - tree.data);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PathSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
