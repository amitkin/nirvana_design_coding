package com.mylearning.snakeladder.betterdesign;

public final class LastSquareRole extends SquareRole {
    public LastSquareRole(Square s) {
        super(s);
    }

    @Override
    public boolean isLastSquare() {
        return true;
    }
 }
