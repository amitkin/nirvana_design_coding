package com.mylearning.design.lift.request.handler;

/**
 * All classes handling {@link Request} must implement this interface.
 * 

 *
 * @param <K>
 * @param <M>
 */
public interface RequestHandler<K, M> {

  /**
   * Processes an incoming requests and returns a response.
   * 
   * @param request
   *          - Incoming requests to be processed.
   * @return response - Response to an input request.
   */
  public M process(K request);

}
