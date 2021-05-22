package com.mylearning.design.solid.examples.chess.moves;

import com.mylearning.design.solid.examples.chess.model.Cell;

/**
 * Given a cell, it will provide next cell which we can reach to.
 */
interface NextCellProvider {
    Cell nextCell(Cell cell);
}
