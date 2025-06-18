package com.mylearning.lld.snakegame;

interface ISnake {
    Position getHead();

    Position getTail();

    int getSize();

    Direction getDirection();

    void moveHeadToNewPosition(Position newPosition, Direction direction);

    void deleteTail();
}
