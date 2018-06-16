package com.mylearning.epi.tree;

import com.mylearning.epi.test_framework.EpiTest;
import com.mylearning.epi.test_framework.GenericTest;
import com.mylearning.epi.tree.BstNode;

public class SearchFirstGreaterValueInBst {

  public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
                                                       Integer k) {

    BstNode<Integer> subtree = tree, firstSoFar = null;
    while (subtree != null) {
      if (subtree.data > k) {
        firstSoFar = subtree;
        subtree = subtree.left;
      } else { // Root and all keys in left-subtree are <= k, so skip them.
        subtree = subtree.right;
      }
    }
    return firstSoFar;
  }

  @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
  public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                 Integer k) {
    BstNode<Integer> result = findFirstGreaterThanK(tree, k);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
