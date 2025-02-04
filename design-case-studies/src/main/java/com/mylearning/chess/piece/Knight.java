package com.mylearning.chess.piece;

import com.mylearning.chess.Board;
import com.mylearning.chess.Box;
import com.mylearning.chess.piece.Piece;

public class Knight extends Piece {
    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {

        // we can't move the piece to a box that has a piece of the same color
        if(end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x * y == 2;
    }
}


