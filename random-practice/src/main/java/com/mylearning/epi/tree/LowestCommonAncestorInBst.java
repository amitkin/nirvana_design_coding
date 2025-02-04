package com.mylearning.epi.tree;

import com.mylearning.epi.test_framework.BinaryTreeUtils;
import com.mylearning.epi.test_framework.EpiTest;
import com.mylearning.epi.test_framework.GenericTest;
import com.mylearning.epi.test_framework.TestFailure;
import com.mylearning.epi.test_framework.TimedExecutor;
import com.mylearning.epi.tree.BstNode;

public class LowestCommonAncestorInBst {

  // Input nodes are nonempty and the key at s is less than or equal to that at
  // b.
  public static BstNode<Integer>
  findLCA(BstNode<Integer> tree, BstNode<Integer> s, BstNode<Integer> b) {

    BstNode<Integer> p = tree;
    while (p.data < s.data || p.data > b.data) {
      // Keep searching since p is outside of [s, b].
      while (p.data < s.data) {
        p = p.right; // LCA must be in p's right child.
      }
      while (p.data > b.data) {
        p = p.left; // LCA must be in p's left child.
      }
    }
    // Now, s.data >= p.data && p.data <= b.data.
    return p;
  }

  @EpiTest(testDataFile = "lowest_common_ancestor_in_bst.tsv")
  public static int lcaWrapper(TimedExecutor executor, BstNode<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BstNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BstNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BstNode<Integer> result = executor.run(() -> findLCA(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can't be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
