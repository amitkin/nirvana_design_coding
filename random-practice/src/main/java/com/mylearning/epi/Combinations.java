package com.mylearning.epi;
import com.mylearning.epi.test_framework.EpiTest;
import com.mylearning.epi.test_framework.EpiTestComparator;
import com.mylearning.epi.test_framework.LexicographicalListComparator;
import com.mylearning.epi.test_framework.GenericTest;

import java.util.List;
import java.util.function.BiPredicate;
public class Combinations {
  @EpiTest(testDataFile = "combinations.tsv")

  public static List<List<Integer>> combinations(int n, int k) {
    // TODO - you fill in here.
    return null;
  }
  @EpiTestComparator
  public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Combinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}