package com.mylearning.lld.snakegame;

import java.util.Scanner;

class Game implements IGame {
    private final IBoard board;
    private final ISnake snake;
    private boolean isFoodProduced;

    public Game(IBoard board, ISnake snake) {
        this.board = board;
        this.snake = snake;
        this.board.produceFood();
        this.isFoodProduced = true;
    }

    @Override
    public boolean move(Direction direction) {
        if (!isFoodProduced) {
            board.produceFood();
            isFoodProduced = true;
        }
        Position startPosition = snake.getHead();
        Position endPosition = snake.getTail();
        Position newStartPosition = new Position(startPosition.getX(), startPosition.getY());

        switch (direction) {
            case RIGHT:
                newStartPosition.setY(newStartPosition.getY() + 1);
                break;
            case LEFT:
                newStartPosition.setY(newStartPosition.getY() - 1);
                break;
            case UP:
                newStartPosition.setX(newStartPosition.getX() - 1);
                break;
            case DOWN:
                newStartPosition.setX(newStartPosition.getX() + 1);
                break;
        }

        if (isValidMove(newStartPosition)) {
            snake.moveHeadToNewPosition(newStartPosition, direction);

            if (!board.isFoodAvailable(newStartPosition)) {
                snake.deleteTail();
                board.deletePosition(endPosition);
            } else {
                isFoodProduced = false;
            }

            board.addPosition(newStartPosition);
            board.displayBoard();
            return true;
        } else {
            System.out.println("Game Over!");
            return false;
        }
    }

    private boolean isValidMove(Position newHeadPosition) {
        if (newHeadPosition.getX() >= board.getSize() || newHeadPosition.getX() < 0 ||
                newHeadPosition.getY() >= board.getSize() || newHeadPosition.getY() < 0) {
            return false;
        }

        Position temp = snake.getHead();
        while (temp != null) {
            if (temp.getX() == newHeadPosition.getX() && temp.getY() == newHeadPosition.getY()) {
                return false;
            }
            temp = temp.getNext();
        }

        return true;
    }

    public static void main(String[] args) {
        int boardSize = 10;
        ISnake snake = new Snake(boardSize, Direction.RIGHT, 3);
        IBoard board = new Board(boardSize, snake.getHead());
        IGame game = new Game(board, snake);

        board.displayBoard();
        boolean wasSuccess = true;
        Scanner scanner = new Scanner(System.in);

        while (wasSuccess) {
            String input = scanner.nextLine();
            Direction direction = Direction.getDirection(input, snake.getDirection());
            wasSuccess = game.move(direction);
        }

        board.displayBoard();
    }
}
