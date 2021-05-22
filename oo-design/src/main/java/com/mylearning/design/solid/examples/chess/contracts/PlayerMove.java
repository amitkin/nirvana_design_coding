package com.mylearning.design.solid.examples.chess.contracts;

import com.mylearning.design.solid.examples.chess.model.Cell;
import com.mylearning.design.solid.examples.chess.model.Piece;
import lombok.Getter;

@Getter
public class PlayerMove {

    Piece piece;
    Cell toCell;
}
