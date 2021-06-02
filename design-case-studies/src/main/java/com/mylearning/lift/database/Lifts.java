package com.mylearning.lift.database;

import static com.mylearning.lift.entities.LiftState.IDLE;

import java.util.ArrayList;
import java.util.List;

import com.mylearning.lift.entities.BasicLift;
import com.mylearning.lift.entities.Lift;
import com.mylearning.lift.entities.LiftType;

/**
 * Acts as an in-memory data store for all lifts current state. In real world
 * cloud applications this data would be stored in a database.
 *
 */
public class Lifts {

  private static int MAX_WEIGHT = 1000;
  public static List<Lift> lifts = new ArrayList<Lift>();

  public static void setup(int floors, int totalLifts) {
    for (int i = 0; i < totalLifts; i++)
      lifts.add(new BasicLift(i + 1, 0, IDLE, LiftType.PASSENGER, 0, floors, MAX_WEIGHT));
  }

}
