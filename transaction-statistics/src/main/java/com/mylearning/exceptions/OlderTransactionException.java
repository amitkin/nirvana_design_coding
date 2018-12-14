package com.mylearning.exceptions;

public class OlderTransactionException extends Exception
{
	private static final long serialVersionUID = 1L;

	public OlderTransactionException(String s){
		super(s);
	}
}
