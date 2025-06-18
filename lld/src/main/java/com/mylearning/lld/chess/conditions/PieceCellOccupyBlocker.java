package com.mylearning.lld.chess.conditions;

import com.mylearning.lld.chess.model.Board;
import com.mylearning.lld.chess.model.Cell;
import com.mylearning.lld.chess.model.Piece;
import com.mylearning.lld.chess.model.Player;

/**
 * This check tells whether a piece can occupy a given cell in the board or not.
 */
public interface PieceCellOccupyBlocker {

    boolean isCellNonOccupiableForPiece(Cell cell, Piece piece, Board board, Player player);
}
