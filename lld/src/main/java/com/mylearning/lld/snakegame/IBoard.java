package com.mylearning.lld.snakegame;

interface IBoard {
    int getSize();

    void displayBoard();

    void addPosition(Position position);

    void deletePosition(Position position);

    void produceFood();

    boolean isFoodAvailable(Position position);
}