package com.mylearning.design.solid.examples.chess.conditions;

import com.mylearning.design.solid.examples.chess.model.Piece;

/**
 * This condition allows a move only if cell is making a move from its initial position. That is first move ever.
 */
public class MoveBaseConditionFirstMove implements MoveBaseCondition {

    public boolean isBaseConditionFullfilled(Piece piece) {
        return piece.getNumMoves() == 0;
    }
}
