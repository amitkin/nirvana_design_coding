package com.mylearning.lld.snakegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTests {
    private IGame game;
    private static final int BOARD_SIZE = 10;

    @BeforeEach
    void setUp() {
        ISnake snake = new Snake(BOARD_SIZE, Direction.UP, 3);
        IBoard board = new Board(BOARD_SIZE, snake.getHead());
        game = new Game(board, snake);
    }

    @ParameterizedTest
    @CsvSource({
            "UP, true",
            "RIGHT, true",
            "DOWN, false",
            "LEFT, false"
    })
    void move_Success(Direction direction, boolean success) {
        boolean wasSuccess = game.move(direction);
        assertEquals(success, wasSuccess);
    }
}
