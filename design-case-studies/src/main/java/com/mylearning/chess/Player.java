package com.mylearning.chess;

public class Player extends Account{
    private Person person;
    private boolean whiteSide = false;

    public Player(Person person, boolean whiteSide){
        this.person = person;
        this.whiteSide = whiteSide;
    }

    public boolean isWhiteSide() {
        return this.whiteSide == true;
    }
}

