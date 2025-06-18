package com.mylearning.lld.snakegame;

import java.util.Random;

class Board implements IBoard {
    private final char[][] board;
    private final int size;

    public Board(int size, Position snakeHead) {
        this.size = size;
        this.board = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '0';
            }
        }
        addSnakePosition(snakeHead);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void addPosition(Position position) {
        board[position.getX()][position.getY()] = 'S';
    }

    @Override
    public void deletePosition(Position position) {
        board[position.getX()][position.getY()] = '0';
    }

    @Override
    public void produceFood() {
        Random random = new Random();
        int x = random.nextInt(size);
        int y = random.nextInt(size);

        while (board[x][y] != '0') {
            x = random.nextInt(size);
            y = random.nextInt(size);
        }
        board[x][y] = 'F';
    }

    @Override
    public boolean isFoodAvailable(Position position) {
        return board[position.getX()][position.getY()] == 'F';
    }

    private void addSnakePosition(Position snakeHead) {
        Position temp = snakeHead;
        while (temp != null) {
            board[temp.getX()][temp.getY()] = 'S';
            temp = temp.getNext();
        }
    }
}
