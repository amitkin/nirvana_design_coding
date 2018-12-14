package com.mylearning.exceptions;

public class InvalidJsonException extends Exception
{
	private static final long serialVersionUID = 1L;

	public InvalidJsonException(String s){
		super(s);
	}
}
