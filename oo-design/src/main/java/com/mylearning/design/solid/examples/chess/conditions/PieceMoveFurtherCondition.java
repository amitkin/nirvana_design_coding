package com.mylearning.design.solid.examples.chess.conditions;

import com.mylearning.design.solid.examples.chess.model.Board;
import com.mylearning.design.solid.examples.chess.model.Cell;
import com.mylearning.design.solid.examples.chess.model.Piece;

/**
 * Condition interface to tell whether a piece can further move from a cell.
 */
public interface PieceMoveFurtherCondition {

    boolean canPieceMoveFurtherFromCell(Piece piece, Cell cell, Board board);
}
