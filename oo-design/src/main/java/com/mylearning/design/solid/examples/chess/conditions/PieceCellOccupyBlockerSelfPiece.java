package com.mylearning.design.solid.examples.chess.conditions;

import com.mylearning.design.solid.examples.chess.model.Board;
import com.mylearning.design.solid.examples.chess.model.Cell;
import com.mylearning.design.solid.examples.chess.model.Piece;
import com.mylearning.design.solid.examples.chess.model.Player;

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
