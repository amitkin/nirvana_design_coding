package com.mylearning;

import java.util.*;

public class Anagram{

    private static int getNum(String word){
        int l = word.length();
        String first  = word.substring(0, l/2);
        String second = word.substring(l/2, l);

        int count = 0;
        for(char ch : first.toCharArray()){
            if(second.indexOf(ch) < 0){
                count += 1;
            }else{
                second = second.replaceFirst(ch+"", "");
            }
        }

        return count;
    }

    /*
    5
    aaabbb
    ab
    abc
    mnop
    xyyx

    output:
    3
    1
    -1
    2
    0
    */
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();

        while(testcases-- > 0){
            // read each string from the stdin
            String current = scanner.next();
            if(current.length()%2 == 1){
                System.out.println(-1);
            }else{
                System.out.println(getNum(current));
            }
        }
    }
}