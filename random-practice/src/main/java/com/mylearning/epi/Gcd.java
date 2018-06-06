package com.mylearning.epi;
import com.mylearning.epi.test_framework.EpiTest;
import com.mylearning.epi.test_framework.GenericTest;
public class Gcd {
  @EpiTest(testDataFile = "gcd.tsv")

  public static long GCD(long x, long y) {
    // TODO - you fill in here.
    return 0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Gcd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
