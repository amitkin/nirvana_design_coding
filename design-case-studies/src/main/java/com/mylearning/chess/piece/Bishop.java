package com.mylearning.chess.piece;

import com.mylearning.chess.Board;
import com.mylearning.chess.Box;

public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return false;
    }
}
