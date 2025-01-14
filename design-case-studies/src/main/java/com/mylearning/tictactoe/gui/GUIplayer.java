package tictactoe.gui;

import com.mylearning.tictactoe.InactivePlayer;
import com.mylearning.tictactoe.InvalidMoveException;

/**
 * Manage interaction with user.  An Appletplayer attempts
 * to make a move it is the current player and when a PlaceListener
 * detects a mouse click.
 *
 * @author $Author: oscar $
 * @version $Id: GUIplayer.java 16633 2008-03-01 22:47:44Z oscar $
 */
public class GUIplayer extends InactivePlayer {

	public GUIplayer(char mark) {
		super(mark);
	}
	
	/**
	 * Attempt to make the given move in the associated game.
	 * @throws InvalidMoveException 
	 */
	public void move(int col, int row) throws InvalidMoveException {
		game.move(col, row, this);
	}
}
