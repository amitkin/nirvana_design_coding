package com.mylearning.onlinetests.amazon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test1 {

    /*Its a sorting problem where we have multiple conditions to be used for sorting.
    Implementation is achieved by writing a custom comparator satisfying all the conditions.*/
    public List<String> orderedJunctionBoxes(int numberOfBoxes, List<String> boxList) {

        Comparator<String> comparator = (s1, s2) -> {
            //first split the string
            String[] s1Arr = s1.split(" ");
            String[] s2Arr = s2.split(" ");

            //lowercase english string in old and number in new
            String id1 = s1Arr[0];
            String id2 = s2Arr[0];

            //alphanumeric in case of old and numeric in case of new
            String version1 = s1.substring(id1.length() + 1);
            String version2 = s2.substring(id2.length() + 1);

            //check first if both are old
            //Both are alphanumeric then tie should be broken by alphanumeric identifier

            if(isAlphanumeric(version1) && !isAlphanumeric(version2)) {
                return -1;
            }
            if(!isAlphanumeric(version1) && isAlphanumeric(version2)) {
                return 1;
            }
            if(isAlphanumeric(version1) && isAlphanumeric(version2)) {
                if(!version1.equals(version2)) {
                    return version1.compareTo(version2);
                } else { //check for tie in case of old
                    return id1.compareTo(id2);
                }
            }
            return 0;
        };

        boxList.sort(comparator);
        return boxList;
    }

    private boolean isAlphanumeric(String str) {
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if(c == ' ') continue;
            if (!Character.isDigit(c))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Test1 a = new Test1();
        //[236 cat dog rabbit snake, 09z cat hamster, az0 first qpx, eo first qpx, ykc 82 01, 06f 12 25 6]
        System.out.println(a.orderedJunctionBoxes(6, Arrays.asList("ykc 82 01", "eo first qpx", "09z cat hamster", "06f 12 25 6", "az0 first qpx", "236 cat dog rabbit snake")));
    }
}
