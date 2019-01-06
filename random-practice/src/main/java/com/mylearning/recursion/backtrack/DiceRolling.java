package com.mylearning.recursion.backtrack;

import java.util.ArrayList;
import java.util.List;

public class DiceRolling {

    List<Integer> diceRolls(int dice){
        List<Integer> result = new ArrayList<>();
        diceRollsHelper(dice, result);
        return result;
    }

    void diceRollsHelper(int dice, List<Integer> chosen){
        if(dice == 0){
            //base case
            System.out.println(chosen);
        } else {
            // for each value of my choice
            for (int i = 1; i <= 6; i++) {
                //choose
                chosen.add(i);

                //search
                diceRollsHelper(dice - 1, chosen);

                //un-choose for backtrack
                chosen.remove(chosen.size() - 1);
            }
        }
    }

    //Try the variation - don't print {1, 1, 5} and {1, 5, 1} both
    List<Integer> diceSum(int dice, int desiredSum){
        List<Integer> result = new ArrayList<>();
        diceSumHelper(dice, desiredSum, 0, result);
        return result;
    }

    void diceSumHelper(int dice, int desiredSum, int sumSoFar, List<Integer> chosen){
        if(dice == 0){
            //base case
            System.out.println(chosen);
        } else {
            // for each value of my choice
            for (int i = 1; i <= 6; i++) {
                int min = sumSoFar + i + 1*(dice-1);
                int max = sumSoFar + i + 6*(dice -1);
                if( min <= desiredSum &&  max >= desiredSum){
                    //choose
                    chosen.add(i);

                    //search
                    diceSumHelper(dice - 1, desiredSum, sumSoFar + i, chosen);

                    //un-choose
                    chosen.remove(chosen.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        DiceRolling diceRolling = new DiceRolling();
        //diceRolling.diceRolls(3);
        diceRolling.diceSum(3, 12);
    }
}
