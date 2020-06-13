package com.mylearning.snakeladder.initialdesign;

public interface IGame {

	boolean isValidPosition(int position);

	void play(IDie die);

	boolean notOver();

	boolean isOver();

	Player currentPlayer();

	void movePlayer(int roll);

	void setSquare(int position, ISquare square);

	Player winner();

	void checkWinner(Player currentPlayer);

	ISquare firstSquare();

	ISquare getSquare(int position);

	String toString();

	void removePlayer(Player player);

	void exchangePlayers(ISquare iSquare, Player playerToSwap);

	boolean playerStillPlaying(Player player);

	void setSquareToLadder(int position, int transport);

	void setSquareToSnake(int position, int transport);

	void setSquareToInstantLose(int position);

	void setSquareToScrambleUp(int position);

	ISquare findSquare(int position, int moves);

	Player getRandomOtherPlayer();

	void playOneRound(IDie die);

}