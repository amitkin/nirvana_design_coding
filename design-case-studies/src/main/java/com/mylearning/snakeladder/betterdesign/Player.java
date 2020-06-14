package com.mylearning.snakeladder.betterdesign;

/*
 * A |Player| is a player in the game defining all actions players can do.
 */
public class Player {
	private String name;
	private Square square;

	private boolean invariant() {
		return name != null
			&& square != null;
	}

	public Player(String name) {
		this.name = name;
		// invariant holds only after joining a game
	}

	public int position() {
		assert invariant();
		return square.getPosition();
	}

	public void moveForward(int moves) {
		assert moves > 0;
		square.leave(this);
		square = square.moveAndLand(moves);
		square.enter(this);
	}
	
	public String toString() {
		return name;
	}

	public Square getSquare() {
		return square;
	}

	public void setSquare(Square newSquare) {
	    square = newSquare;
	}

	public String getName() {
		return name ;
	}

	public boolean wins() {
		return square.isLastSquare();
	}

}
