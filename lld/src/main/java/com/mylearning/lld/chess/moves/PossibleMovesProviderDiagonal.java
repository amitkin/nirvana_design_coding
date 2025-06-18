package com.mylearning.lld.chess.moves;

import com.mylearning.lld.chess.conditions.MoveBaseCondition;
import com.mylearning.lld.chess.conditions.PieceCellOccupyBlocker;
import com.mylearning.lld.chess.conditions.PieceMoveFurtherCondition;
import com.mylearning.lld.chess.model.Board;
import com.mylearning.lld.chess.model.Cell;
import com.mylearning.lld.chess.model.Piece;
import com.mylearning.lld.chess.model.Player;

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
