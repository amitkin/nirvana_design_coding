package com.mylearning.lld.snakegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SnakeTests {
    private ISnake snake;
    private static final int BOARD_SIZE = 10;

    @BeforeEach
    void setUp() {
        snake = new Snake(BOARD_SIZE, Direction.UP, 3);
    }

    @Test
    void moveHeadToNewPosition_MovesToNextPositionSuccess() {
        Position headPreviousPosition = snake.getHead();
        Position headNewPosition = new Position(headPreviousPosition.getX(), headPreviousPosition.getY() + 1);
        Direction direction = Direction.RIGHT;

        snake.moveHeadToNewPosition(headNewPosition, direction);

        assertEquals(headNewPosition.getX(), snake.getHead().getX());
        assertEquals(headNewPosition.getY(), snake.getHead().getY());
        assertEquals(direction, snake.getDirection());
    }

    @Test
    void deleteTail_MakesSecondLastElementTailSuccess() {
        Position headPosition = snake.getHead();
        Position secondLastNode = getSecondLastElementFromLinkedList(headPosition);

        snake.deleteTail();

        assertEquals(secondLastNode.getX(), snake.getTail().getX());
        assertEquals(secondLastNode.getY(), snake.getTail().getY());
    }

    private Position getSecondLastElementFromLinkedList(Position head) {
        Position temp = head;
        Position tempForward = head.getNext();
        while (tempForward.getNext() != null) {
            tempForward = tempForward.getNext();
            temp = temp.getNext();
        }
        return temp;
    }
}