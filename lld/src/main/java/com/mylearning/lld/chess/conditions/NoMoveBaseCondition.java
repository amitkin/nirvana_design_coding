package com.mylearning.lld.chess.conditions;

import com.mylearning.lld.chess.model.Piece;

/**
 * Helper implementation to use when there is no associated base condition with a move.
 */
public class NoMoveBaseCondition implements MoveBaseCondition {
    public boolean isBaseConditionFullfilled(Piece piece) {
        return true;
    }
}
