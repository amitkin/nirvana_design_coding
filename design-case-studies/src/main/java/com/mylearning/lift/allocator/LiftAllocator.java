package com.mylearning.lift.allocator;

import com.mylearning.lift.entities.Lift;
import com.mylearning.lift.request.LiftAllocationRequest;

/**
 * All classes that perform the allocation of an {@link Lift} must implement
 * this interface.
 *
 */
public interface LiftAllocator {

  /**
   * Allocates a {@link Lift} from the input lift pool & request.
   * 
   * @param liftPool
   *          - Lift pool from which a list of {@link Lift} is allocated.
   * @param request
   *          - A {@link LiftAllocationRequest} object based on which allocation
   *          for {@link Lift} will take place.
   * @return {@link Lift} - An allocated {@link Lift} to satisfy the
   *         {@link LiftAllocationRequest} request.
   */
  public Lift allocate(Object liftPool, LiftAllocationRequest request);

}