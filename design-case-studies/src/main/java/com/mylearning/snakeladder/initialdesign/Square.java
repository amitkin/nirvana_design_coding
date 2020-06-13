package com.mylearning.snakeladder.initialdesign;

/*
 * A |Square| is a field on our game board holding one player.
 */
public class Square implements ISquare {

	protected int position;
	protected IGame game;
	protected Player player;

	private boolean invariant() {
		return game != null
			&& game.isValidPosition(position);
	}

	public Square(IGame game, int position) {
		this.game = game;
		this.position = position;
		assert invariant();
	}

	public int position() {
		return this.position;
	}

	public ISquare moveAndLand(int moves) {
		assert moves >= 0;
		return game.findSquare(position, moves).landHereOrGoHome();
	}

	protected ISquare nextSquare() {
		return game.getSquare(position+1);
	}

	protected ISquare previousSquare() {
		return game.getSquare(position-1);
	}

	public ISquare landHereOrGoHome() {
		return this.isOccupied() ? game.firstSquare() : this;
	}

	public String toString() {
		return "[" + this.squareLabel() + this.player() + "]";
	}
	
	protected String squareLabel() {
		return Integer.toString(position);
	}
	
	public boolean isOccupied() {
		return player != null;
	}

	public void enter(Player player) {
		assert this.player == null;
		this.player = player;
	}
	
	// place holder so we can override it in ScrambleUp
	public void enter(Player player, boolean swapping) {
	    enter(player);
    }

	public void leave(Player player) {
		assert this.player == player;
		this.player = null;
	}

	public boolean isFirstSquare() {
		return false;
	}

	public boolean isLastSquare() {
		return false;
	}

	protected String player() {
		return this.isOccupied() ? ("<" + this.player + ">") : "";
	}
}
