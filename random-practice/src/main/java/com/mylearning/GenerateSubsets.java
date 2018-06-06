package com.mylearning;

import java.util.ArrayList;
import java.util.List;

public class GenerateSubsets {
    static List<String> generateSets(char[] str, int str_size){
        List<String> subsetStrings = new ArrayList<>();
        long subsets = (long)Math.pow(2, str_size);
        for(int i=0; i< subsets; i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<str_size; j++) {
                //Check if jth bit is set in i
                if (((1 << j) & i) > 0)
                    sb.append(str[j]);
            }
            if(sb.length() > 0)
                subsetStrings.add(sb.toString());
        }
        return subsetStrings;
    }

    public static void main(String[] args){
        List<String> strings = generateSets("amit".toCharArray(), 4);
        System.out.println(strings);
    }
}
