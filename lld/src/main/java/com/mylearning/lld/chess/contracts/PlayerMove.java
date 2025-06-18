package com.mylearning.lld.chess.contracts;

import com.mylearning.lld.chess.model.Cell;
import com.mylearning.lld.chess.model.Piece;
import lombok.Getter;

@Getter
public class PlayerMove {

    Piece piece;
    Cell toCell;
}
