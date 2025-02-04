package com.mylearning.epi;

import com.mylearning.epi.test_framework.EpiTest;
import com.mylearning.epi.test_framework.GenericTest;

public class SpreadsheetEncoding {
  @EpiTest(testDataFile = "spreadsheet_encoding.tsv")

  public static int ssDecodeColID(final String col) {

    return col.chars().reduce(0, (result, c) -> result * 26 + c - 'A' + 1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpreadsheetEncoding.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
