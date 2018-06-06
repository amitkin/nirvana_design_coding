package com.mylearning.epi;
import com.mylearning.epi.test_framework.EpiTest;
import com.mylearning.epi.test_framework.GenericTest;
public class RegularExpression {
  @EpiTest(testDataFile = "regular_expression.tsv")

  public static boolean isMatch(String regex, String s) {
    // TODO - you fill in here.
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RegularExpression.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
