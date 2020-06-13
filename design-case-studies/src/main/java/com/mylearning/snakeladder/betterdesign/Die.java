package com.mylearning.snakeladder.betterdesign;

/*
 * The |Die| randomly defines how many squares (a positive number
 * and always less or equal to |FACES|) the player moves forward.
 */
public class Die {
	private static final int MINVALUE = 1;
	private static final int MAXVALUE = 6;

	public int roll () {
		return random ( MINVALUE , MAXVALUE );
	}

	private int random (int min , int max ) {
		assert min < max;
		return ( int) ( min + Math . round (( max - min) * Math . random ()));
	}
}
