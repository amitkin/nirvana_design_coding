package com.mylearning.tictactoe;

import static org.junit.Assert.*;
import java.io.PrintStream;
import org.junit.Before;

/**
 * @author $Author: oscar $
 * @version $Id: AbstractBoardGameTest.java 16609 2008-03-01 16:29:27Z oscar $
 */
public abstract class AbstractBoardGameTest {
	protected PrintStream out, err;
	protected BoardGame game;
	Player X;
	Player O;
	
	public AbstractBoardGameTest() {
		silent();
		// verbose(); // useful when debugging test cases
	}
	
	protected void silent() {
		out = err = new PrintStream(new NullOutputStream());
	}

	protected void verbose() {
		out = System.out;
		err = System.err;
	}

	@Before public void setUp() throws Exception {
		X = new StreamPlayer('X');
		O = new StreamPlayer('O');
		game = makeGame(X, O);
	}
		
	/**
	 * Run a game with simulated input for X and Y, and check that
	 * the winner is as expected.
	 * Game results are sent to a NullOutputStream.
	 */
	public void checkGame(String Xmoves, String Omoves, String winner, int squaresLeft) {
		X = new StreamPlayer('X', Xmoves);
		O = new StreamPlayer('O', Omoves);
		game = makeGame(X,O);
		GameDriver.playGame(game, out, err);
		assertEquals(winner, game.winner().toString());
		assertEquals(squaresLeft, game.squaresLeft());
	}
	
	/**
	 * Factory method so subclasses can create other games.
	 */
	abstract protected BoardGame makeGame(Player X, Player O);

	/**
	 * Run a command that should fail.
	 */
	public void assertFails(Runnable command) {
		boolean caught = false;
		try {
			command.run();
		} catch(AssertionError err) {
			caught = true;
		}
		assertTrue(caught);
	}
}
