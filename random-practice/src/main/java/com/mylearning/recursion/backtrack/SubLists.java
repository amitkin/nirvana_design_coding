package com.mylearning.recursion.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubLists {

    void subListGenerator(List<String> strings){
        subListGeneratorHelper(new LinkedList<>(strings), new ArrayList<>());
    }

    void subListGeneratorHelper(List<String> strings, List<String> chosen){
        if(strings.size() == 0){
            System.out.println(chosen);
        } else {

            //Understand the choice first, here its not choosing all available strings
            //but whether particular string will be chosen or not

            String mine = strings.get(0);
            strings.remove(mine);
            chosen.add(mine);

            //explore/search for both options - yes include them and not include them
            subListGeneratorHelper(strings, chosen);

            chosen.remove(chosen.size() - 1);
            subListGeneratorHelper(strings, chosen);

            //unchoose for backtrack
            strings.add(mine);
        }
    }

    public static void main(String[] args) {
        SubLists s = new SubLists();
        s.subListGenerator(Arrays.asList("Jane", "Bob", "Matt", "Sara"));
    }
}
