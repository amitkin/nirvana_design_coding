package com.mylearning.snakeladder.initialdesign;

import java.util.ArrayList;
import java.util.List;

/*
 * The |FirstSquare| is the first square on the board holding all players in
 * the beginning and every player who lands on an occupied square.
 */
public class FirstSquare extends Square {

	private List<Player> players;

	public FirstSquare(IGame game, int position) {
		super(game, position);
		players = new ArrayList<Player>();
	}

	public ISquare landHereOrGoHome() {
		return this;
	}

	@Override
	public boolean isOccupied() {
		return !players.isEmpty();
	}

	@Override
	public void enter(Player player) {
		assert !players.contains(player);
		players.add(player);
	}

	@Override
	public void leave(Player player) {
		assert players.contains(player);
		players.remove(player);
	}

	@Override
	public boolean isFirstSquare() {
		return true;
	}

	@Override
	protected String player() {
		StringBuffer buffer = new StringBuffer();
		for (Player player : players) {
			buffer.append("<" + player + ">");
		}
		return buffer.toString();
	}
}
