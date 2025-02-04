package com.mylearning.lift.response;

/**
 * All responses must implement this interface.
 * 

 */
public interface Response<T> {

  /**
   * All {@link Response} type of objects must returns an identifier to uniquely
   * identify a response. Generally all requests and their corresponding
   * response have the same identifier.
   * 
   * @return Response Identifier
   */
  public T getId();

}
