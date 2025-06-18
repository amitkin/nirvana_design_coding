package com.mylearning.lld.chess.conditions;

import com.mylearning.lld.chess.model.Board;
import com.mylearning.lld.chess.model.Cell;
import com.mylearning.lld.chess.model.Piece;
import com.mylearning.lld.chess.model.Player;

/**
 * This tells that a cell cannot occupy a cell if that cell already has any piece from the same player.
 */
public class PieceCellOccupyBlockerSelfPiece implements PieceCellOccupyBlocker {

    @Override
    public boolean isCellNonOccupiableForPiece(Cell cell, Piece piece, Board board, Player player) {
        if (cell.isFree()) {
            return false;
        }
        return cell.getCurrentPiece().getColor() == piece.getColor();
    }
}
