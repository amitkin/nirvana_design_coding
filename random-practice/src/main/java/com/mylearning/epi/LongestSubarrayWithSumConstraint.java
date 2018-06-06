package com.mylearning.epi;
import com.mylearning.epi.test_framework.EpiTest;
import com.mylearning.epi.test_framework.GenericTest;
import java.util.List;
public class LongestSubarrayWithSumConstraint {
  @EpiTest(testDataFile = "longest_subarray_with_sum_constraint.tsv")

  public static int findLongestSubarrayLessEqualK(List<Integer> A, int k) {
    // TODO - you fill in here.
    return 0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestSubarrayWithSumConstraint.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
