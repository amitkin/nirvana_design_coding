package com.mylearning.lift.request.handler;

import com.mylearning.lift.allocator.ClosestInPathLiftAllocator;
import com.mylearning.lift.database.Lifts;
import com.mylearning.lift.request.LiftAllocationRequest;
import com.mylearning.lift.response.LiftAllocationResponse;

/**
 * Handles a {@link LiftAllocationRequest} and returns a
 * {@link LiftAllocationResponse} with the allocated lift information
 * 

 */
public class LiftAllocationRequestHandler extends BaseRequestHandler<LiftAllocationRequest, LiftAllocationResponse> {

  private ClosestInPathLiftAllocator liftAllocator = new ClosestInPathLiftAllocator();

  /**
   * Processes an incoming {@Link LiftAllocationRequest} and returns a
   * {@link LiftAllocationResponse} with the reserved lift information. Uses
   * closest in path lift algorithm for allocating lift.
   */
  public LiftAllocationResponse process(LiftAllocationRequest request) {
    return new LiftAllocationResponse(request.getId(), liftAllocator.allocate(Lifts.lifts, request));
  }

}
