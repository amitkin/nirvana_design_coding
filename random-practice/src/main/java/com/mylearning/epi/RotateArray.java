package com.mylearning.epi;
import com.mylearning.epi.test_framework.EpiTest;
import com.mylearning.epi.test_framework.GenericTest;
import com.mylearning.epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
public class RotateArray {

  public static void rotateArray(int rotateAmount, List<Integer> A) {
    // TODO - you fill in here.
    return;
  }
  @EpiTest(testDataFile = "rotate_array.tsv")
  public static List<Integer>
  rotateArrayWrapper(TimedExecutor executor, List<Integer> A, int rotateAmount)
      throws Exception {
    List<Integer> aCopy = new ArrayList<>(A);

    executor.run(() -> rotateArray(rotateAmount, aCopy));
    return aCopy;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RotateArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
