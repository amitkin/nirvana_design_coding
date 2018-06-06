package com.mylearning.epi;
import com.mylearning.epi.test_framework.EpiTest;
import com.mylearning.epi.test_framework.GenericTest;
import java.util.List;
public class Arbitrage {
  @EpiTest(testDataFile = "arbitrage.tsv")

  public static boolean isArbitrageExist(List<List<Double>> graph) {
    // TODO - you fill in here.
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Arbitrage.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
