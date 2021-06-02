package com.mylearning.lift.response;

import com.mylearning.lift.entities.Lift;
import com.mylearning.lift.request.LiftAllocationRequest;

/**
 * Represents an allocation response to a {@link LiftAllocationRequest}.
 * 

 */
public class LiftAllocationResponse extends BaseResponse<String> {

  private Lift lift;

  public LiftAllocationResponse(String id, Lift lift) {
    super();
    this.id = id;
    this.lift = lift;
  }

  public Lift getLift() {
    return lift;
  }

  public void setLift(Lift lift) {
    this.lift = lift;
  }

}
