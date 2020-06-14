# Second exercise sheet #

Modify the Snakes and Ladders game from the lecture in the following ways:

1. Add two new types of squares: `InstantLose` and `ScrambleUp`. The `InstantLose` takes the player that steps on it out of the game, while letting the others play on. The `ScrambleUp` field chooses randomly the piece of another player and exchanges it with the one that stepped on the `ScrambleUp` field. 

2. Write a unit test `InstantLoseTest` that verifies that `InstantLose` works as intended.

3. Add more test methods to `ScrambleUpTest` that verify that your ScrambleUp works as intended. Feel free to change the existing parts of the test.

4. Modify the game such that the Game.main method will run on a field with a `InstantLose` and `ScrambleUp`. The `InstantLose`  and `ScrambleUp` should be distinguishable in the textual output of the game.

5. Add API documentation to the snakes.ISquare interface according to the Oracle-provided rules (see [http://java.sun.com/j2se/javadoc/writingdoccomments/](http://java.sun.com/j2se/javadoc/writingdoccomments/)
