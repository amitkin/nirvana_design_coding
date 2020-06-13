The previous design has several problems with regard to the design patterns Grasp and some others. 
For example, class Game is quite big, specially method createGame(). This is because it does too much. 
We could add a class Board  instead of the simple list of squares in Game , and move several methods there to simplify the Game class.

Second, the hierarchy derived from ISquare is it all right according the the Coad rules on when to create subclasses? 
Are the different types of squares extending the methods of the parent class ? Or are all the same only that behave according to different rules ? 

This design tries to address these problems.
