package com.mylearning.lld.snakegame;

enum Direction {
    RIGHT, LEFT, UP, DOWN;

    public static Direction getDirection(String input, Direction defaultDirection) {
        switch (input.toUpperCase()) {
            case "R":
                return RIGHT;
            case "L":
                return LEFT;
            case "U":
                return UP;
            case "D":
                return DOWN;
        }
        return defaultDirection;
    }
}
