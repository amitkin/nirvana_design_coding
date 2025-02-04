package com.mylearning.snakeladder.betterdesign;

/*
 * The |Game| is the core class which defines everything general about the game.
 */
import java.util.LinkedList;
import java.util.Queue;

public final class Game {
	private Queue<Player> players = new LinkedList<>();
	// this is a queue : elements are removed from the beginning
	// with players . remove () and added to the end by players . add ()
	private Board board;
	private Player winner;

	public Game(String[] playerNames , int numSquares , int[][] snakes , int[][] ladders) {
		populateBoard(numSquares, ladders, snakes);
		populatePlayers(playerNames);
	}

	private void populateBoard(int numSquares, int[][] ladders , int[][] snakes) {
		board = new Board(numSquares, ladders, snakes);
	}

	private void populatePlayers(String [] playerNames) {
		assert playerNames . length >0 : " There must be some player " ;
		System.out.println("Players are : ");
		int i=1;
		for(String str : playerNames) {
			Player player = new Player(str);
			players.add(player); // adds to the end
			System.out.println(i + ". " + str );
			i++;
		}
	}

	public void play() {
		assert ! players.isEmpty() : "No players to play ";
		assert board != null : "No scoreboard to play ";
		Die die = new Die();
		startGame();

		System.out.println ("Initial state : \n" + this );
		while(notOver()) {
			int roll = die.roll();
			System.out.println("Current player is " + currentPlayer () + " and rolls " + roll);
			movePlayer(roll);
			System.out.println("State : \n" + this );
		}
		System.out.println(winner + " has won.");
	}

	private void movePlayer(int roll) {
		Player currentPlayer = players.remove(); // from the head
		currentPlayer.moveForward(roll);
		players.add(currentPlayer); // to the tail
		if(currentPlayer.wins()) {
			winner = currentPlayer ;
		}
	}

	private void startGame () {
		placePlayersAtFirstSquare ();
		winner = null ;
	}

	private void placePlayersAtFirstSquare () {
		for ( Player player : players ) {
			board.firstSquare().enter(player);
		}
	}

	private boolean notOver () {
		return winner == null ;
	}

	@Override
	public String toString () {
		StringBuilder str = new StringBuilder();
		for ( Player player : players ) {
			str.append(player.getName()).append(" is at square ").append(player.position() + 1).append("\n");
		}
		return str.toString();
	}

	private Player currentPlayer() {
		assert players.size()>0;
		return players.peek();
	}
 }