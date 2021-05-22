package com.mylearning.design.solid.examples.chess.moves;

import com.mylearning.design.solid.examples.chess.conditions.MoveBaseCondition;
import com.mylearning.design.solid.examples.chess.conditions.PieceCellOccupyBlocker;
import com.mylearning.design.solid.examples.chess.conditions.PieceMoveFurtherCondition;
import com.mylearning.design.solid.examples.chess.model.Board;
import com.mylearning.design.solid.examples.chess.model.Cell;
import com.mylearning.design.solid.examples.chess.model.Piece;
import com.mylearning.design.solid.examples.chess.model.Player;

import java.util.List;

public class PossibleMovesProviderDiagonal extends PossibleMovesProvider {


    public PossibleMovesProviderDiagonal(int maxSteps, MoveBaseCondition baseCondition,
                                         PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
    }

    @Override
    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        return null;
    }
}
