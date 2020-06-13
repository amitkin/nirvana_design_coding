package com.mylearning.snakeladder.initialdesign;

/*
 * AK you should document, what the responsibilities of the class/interface are.
 * 
 * The |ISquare| is the interface from which is implemented by all other squares.
 */
public interface ISquare {
    /**
     * Returns the position of the square
     *
     * @return      position of the square
     */
	// AK does it start counting with 0 or 1? Is the position unique?
	int position();
	
	/**
     * This method returns the square to land on.
     *
     * @param  moves  the amount of moves the player makes
     * @return        the square to land on
     */
	ISquare moveAndLand(int moves);
	
	/**
     * Returns whether the square is the first one or not.
     *
     * @return      boolean value whether it is the first square or not
     */
	// AK what's so special about the first square?
	boolean isFirstSquare();
	
	/**
     * Returns whether the square is the first one or not.
     *
     * @return      boolean value whether it is the last square or not
     */
	boolean isLastSquare();
	
	/**
     * Enters the square.
     *
     * @param  player  the player who should enter this field
     * @return         void
     */
	// AK what happens if the square is occupied? Should I use this method?
	void enter(Player player);
	
	/**
     * Leaves the square.
     *
     * @param  player  the player who should leave this field
     * @return         void
     */
	void leave(Player player);
	
	/**
     * Returns whether this square is occupied or not.
     *
     * @return      occupation state of the square
     */
	boolean isOccupied();
	
	/**
     * Returns the square the requesting player should land on. Either it's this square
     * or the first square if it's occupied.
     *
     * @return      returns the square to land on as object
     */
	ISquare landHereOrGoHome();
}
