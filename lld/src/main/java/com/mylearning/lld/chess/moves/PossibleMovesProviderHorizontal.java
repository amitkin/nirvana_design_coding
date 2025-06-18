package com.mylearning.lld.chess.moves;

import com.mylearning.lld.chess.conditions.MoveBaseCondition;
import com.mylearning.lld.chess.conditions.PieceCellOccupyBlocker;
import com.mylearning.lld.chess.conditions.PieceMoveFurtherCondition;
import com.mylearning.lld.chess.model.Board;
import com.mylearning.lld.chess.model.Cell;
import com.mylearning.lld.chess.model.Piece;
import com.mylearning.lld.chess.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PossibleMovesProviderHorizontal extends PossibleMovesProvider {

    public PossibleMovesProviderHorizontal(int maxSteps, MoveBaseCondition baseCondition,
                                           PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
    }

    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, final Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        result.addAll(findAllNextMoves(piece, board::getLeftCell, board, additionalBlockers, player));
        result.addAll(findAllNextMoves(piece, board::getRightCell, board, additionalBlockers, player));
        return result;
    }
}
