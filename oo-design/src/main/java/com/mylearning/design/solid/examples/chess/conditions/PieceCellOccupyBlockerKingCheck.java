package com.mylearning.design.solid.examples.chess.conditions;

import com.mylearning.design.solid.examples.chess.model.*;

/**
 * This tells whether making piece move to a cell will attract check for king.
 */
public class PieceCellOccupyBlockerKingCheck implements PieceCellOccupyBlocker {

    @Override
    public boolean isCellNonOccupiableForPiece(final Cell cell, final Piece piece, final Board board, Player player) {
        Cell pieceOriginalCell = piece.getCurrentCell();
        piece.setCurrentCell(cell);
        boolean playerGettingCheckByMove = board.isPlayerOnCheck(player);
        piece.setCurrentCell(pieceOriginalCell);
        return playerGettingCheckByMove;
    }
}
