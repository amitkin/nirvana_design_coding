package com.mylearning.chess.piece;

import com.mylearning.chess.Board;
import com.mylearning.chess.Box;
import com.mylearning.chess.piece.Piece;

public class Rook extends Piece {

    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return false;
    }
}
