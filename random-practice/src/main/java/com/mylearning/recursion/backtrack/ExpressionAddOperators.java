package com.mylearning.recursion.backtrack;

/*
Facebook - Phone Interview
Input: string digits and int target
print (not return list - I care about memory) by injecting "+" "-" to get the calculations that work

example 1
'123' 24
Print:
"1+23"

example 2
'10' 1
Print:
'1+0'
'1-0'

example 3
'100' 1
Print:
'1+00'
'1+0+0'
'1+0-0'
'1-00'
'1-0+0'
'1-0-0'

example 4
'10' 10
Print:
'10'

example 5
'10' -1
Print:
'-1+0'
'-1-0'

example 6
'10' -6
Print:

Hint: No dynamic programing / memoize
*/
public class ExpressionAddOperators {

    public void addOperators(String num, int target) {
        if(num == null || num.length() == 0) {
            return;
        }
        dfs("", num, 0, 0, target);
    }

    private void dfs(String path, String num, int pos, long eval, int target) {
        if(pos == num.length()) {
            if(eval == target) {
                if(path.charAt(0) == '+') {
                    path = path.substring(1);
                }
                System.out.println(path);
                return;
            }
        }

        for(int i=pos; i<num.length(); i++) {
            String temp = num.substring(pos, i + 1);
            long cur = Long.parseLong(temp);
            dfs(path + "+" + temp, num, i+1, eval + cur, target);
            dfs(path + "-" + temp, num, i+1, eval - cur, target);
        }
    }

    public static void main(String[] args) {
        ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();
        expressionAddOperators.addOperators("123", 24);
        System.out.println();
        expressionAddOperators.addOperators("10", 1);
        System.out.println();
        expressionAddOperators.addOperators("100", 1);
        System.out.println();
        expressionAddOperators.addOperators("10", 10);
        System.out.println();
        expressionAddOperators.addOperators("10", -1);
        System.out.println();
        expressionAddOperators.addOperators("10", -6);
    }
}








