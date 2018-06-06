package com.mylearning.epi;
import com.mylearning.epi.test_framework.EpiTest;
import com.mylearning.epi.test_framework.EpiUserType;
import com.mylearning.epi.test_framework.GenericTest;
import java.util.List;
public class LineThroughMostPoints {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Point {
    public int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  @EpiTest(testDataFile = "line_through_most_points.tsv")

  public static int findLineWithMostPoints(List<Point> points) {
    // TODO - you fill in here.
    return 0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LineThroughMostPoints.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
