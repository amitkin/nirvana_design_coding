package com.mylearning.design.lift.response.handler;

/**
 * All response handlers must implement this interface. It defines a required
 * contract for all response handlers.
 * 

 *
 * @param <K>
 * @param <M>
 */
public interface ResponseHandler<K, M> {

  /**
   * Any action to be performed using the response object can be implemented
   * here.
   * 
   * @param response
   *          - Response to be processed.
   * @return modifiedResponse - The response after processing.
   */
  public K dispatch(M response);
  
}
