package com.mylearning.design.solid.examples.chess.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.mylearning.design.solid.examples.chess.helpers.TestHelpers;
import org.junit.Test;

public class CellTest {

    @Test
    public void testFreeCell() {
        Cell cell = new Cell(0, 0);
        assertTrue(cell.isFree());
    }

    @Test
    public void testOccupiedCell() {
        Cell cell = new Cell(0, 0);
        cell.setCurrentPiece(TestHelpers.randomPiece());
        assertFalse(cell.isFree());
    }
}