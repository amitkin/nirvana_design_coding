package com.mylearning.design.patterns.pluralsight.behavioral.chain.concur;

public class Director extends Handler {

	@Override
	public void handleRequest(Request request) {
		if(request.getRequestType() == RequestType.CONFERENCE) {
			System.out.println("Directors can approve conferences");
		}
		else {
			successor.handleRequest(request);
		}
	}
}
