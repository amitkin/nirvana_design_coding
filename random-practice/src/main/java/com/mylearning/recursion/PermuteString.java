package com.mylearning.recursion;

import java.util.ArrayList;
import java.util.List;

//This comes under exhaustive search - https://www.youtube.com/watch?v=p_YlKFD88s8
//Playlist - https://www.youtube.com/watch?v=QSfwf4D-GCQ&list=PL_MAtxUzuk68qQNUOTzPWVAdzySpPrsEM
public class PermuteString {

    List<String> permute(String s){
        List<String> result = new ArrayList<>();
        permuteHelper(s, "", result);
        return result;
    }

    void permuteHelper(String s, String prefix, List<String> result) {
        //Base case here is no longer simple case rather it is case where algorithm is finished
        //working and has no more choices left to make.
        if(s.length() == 0){
            result.add(prefix);
        }
        //for each available choices choose and search the remaining decisions that could follow choice
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            String s2 = s.substring(0, i) + s.substring(i+1);
            permuteHelper(s2, prefix + ch, result);
        }
    }

    public static void main(String[] args) {
        PermuteString permuteString = new PermuteString();
        System.out.println(permuteString.permute("AMIT"));
    }
}
