package com.mylearning.lld.snakegame;

class Snake implements ISnake {
    private Position head;
    private Position tail;
    private int size;
    private Direction direction;

    public Snake(int boardSize, Direction direction, int snakeSize) {
        this.size = snakeSize;
        this.direction = direction;
        initializeSnakePosition(boardSize, snakeSize);
    }

    @Override
    public Position getHead() {
        return head;
    }

    @Override
    public Position getTail() {
        return tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void moveHeadToNewPosition(Position newPosition, Direction direction) {
        this.direction = direction;
        newPosition.setNext(head);
        head = newPosition;
        size++;
    }

    @Override
    public void deleteTail() {
        Position temp = head;
        Position tempForward = head.getNext();

        while (tempForward.getNext() != null) {
            tempForward = tempForward.getNext();
            temp = temp.getNext();
        }
        temp.setNext(null);
        tail = temp;
        size--;
    }

    private void initializeSnakePosition(int boardSize, int snakeSize) {
        int snakeX = boardSize - 1;
        int snakeY = snakeSize - 1;

        head = new Position(snakeX, snakeY);
        Position temp = head;

        while (snakeSize > 1) {
            snakeY--;
            snakeSize--;
            temp.setNext(new Position(snakeX, snakeY));
            temp = temp.getNext();
        }
        tail = temp;
    }
}
