package com.mylearning.coinflip;

public class Coin {

    private String coinOption = "";
    public String[] coinValue = {"Heads", "Tails"};

    Coin(){

        // A random value of 0 or 1 is calculated
        // The value of coinOption is set based on
        // the random index chosen from coinValue[]

        int randNum = (Math.random() < 0.5)?0:1;
        coinOption = coinValue[randNum];

    }

    public String getCoinOption(){
        return coinOption;
    }

}
