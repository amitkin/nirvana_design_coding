package com.mylearning.recursion.backtrack;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        //backtrack(0, 0, "", n, result);
        backtrack(n, n, "", result);
        return result;
    }

    public void backtrack(int open, int close, String prefix, int max, List<String> result){
        //System.out.println(" backtrack (" + open + ", " + close + ", " + max + " )");
        //base case
        if(prefix.length() == max*2){
            result.add(prefix);
            return;
        }

        //Choose
        if(open < max) {
            backtrack(open + 1, close, prefix + "(", max, result);
        }
        if(close < open) { //This helps in ensuring parenthesis are well balanced
            backtrack(open, close + 1, prefix + ")", max, result);
        }
    }

    public void backtrack(int open, int close, String prefix, List<String> result){
        //System.out.println(" backtrack (" + open + ", " + close + ") - " + prefix);
        //base case
        if(close < open){ // open is greater means close parenthesis is chosen more
            return;
        }
        if(open == 0 && close == 0){
            result.add(prefix);
            return;
        }

        //Choose
        if(open > 0) {
            backtrack(open - 1, close, prefix + "(", result);
        }
        if(close > 0) { //This helps in ensuring parenthesis are well balanced
            backtrack(open, close - 1, prefix + ")", result);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis g = new GenerateParenthesis();
        System.out.println(g.generateParenthesis(3));
    }
}

/*
2,2,2 - ()() - output
2,1,2 - ()(
1,1,2 - ()

2,2,2 - (()) - output
2,1,2 - (()
2,0,2 - ((
1,0,2 - (
0,0,2
*/

/*
3,3,3 - ()()() - output
3,2,3 - ()()(
2,2,3 - ()()
2,1,3 - ()(
1,1,3 - ()

3,3,3 - (())() - output
3,2,3 - (())(
2,2,3 - (())

3,3,3 - (()()) - output
3,2,3 - (()()
3,1,3 - (()(
2,1,3 - (()

3,3,3 - ((())) - output
3,2,3 - ((())
3,1,3 - ((()
3,0,3 - (((
2,0,3 - ((
1,0,3 - (
0,0,3
*/