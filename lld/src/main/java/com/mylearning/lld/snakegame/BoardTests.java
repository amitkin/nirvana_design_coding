package com.mylearning.lld.snakegame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTests {
    private IBoard board;
    private static final int BOARD_SIZE = 10;

    @BeforeEach
    void setUp() {
        Snake snake = new Snake(BOARD_SIZE, Direction.UP, 3);
        board = new Board(BOARD_SIZE, snake.getHead());
    }

    @Test
    void displayBoard_HappyPath_Success() {
        board.displayBoard();
    }

    @Test
    void addPosition_HappyPath_Success() {
        Position position = new Position(2, 3);
        board.addPosition(position);
    }

    @Test
    void deletePosition_HappyPath_Success() {
        Position position = new Position(2, 3);
        board.deletePosition(position);
    }

    @Test
    void produceFood_HappyPath_Success() {
        board.produceFood();
    }

    @Test
    void isFoodAvailable_Success() {
        Board newBoard = new Board(BOARD_SIZE, new Position(0, 0));
        Position position = new Position(3, 3);
        boolean isFoodAvailable = newBoard.isFoodAvailable(position);

        assertFalse(isFoodAvailable);
    }
}