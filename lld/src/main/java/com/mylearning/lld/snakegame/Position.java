package com.mylearning.lld.snakegame;

class Position {
    private int x;
    private int y;
    private Position next;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position getNext() {
        return next;
    }

    public void setNext(Position next) {
        this.next = next;
    }
}
