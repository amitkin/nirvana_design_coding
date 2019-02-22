package com.mylearning.onlinetests.skyscanner;

import java.util.*;

/*
5
Sarah
Claudia
June Sarah
Sarah Tom
Tom Simon
Tom Claudia

Output:
Sarah

6
Sarah
Fred
June Tom
Tom Nathan
Tom Sarah
Nathan Qing
Nathan Fred

*/
public class CommonManager {

    /*
     * Complete the function below.
     */

    static void OutputCommonManager(int count, Scanner in) {
        Map<String, String> empToManagerMap = new HashMap<>();

        String personA = in.nextLine();
        String personB = in.nextLine();
        while (in.hasNextLine()) {
            String str = in.nextLine();
            if (str.length() > 0) {
                String a = str.split(" ")[0];
                String b = str.split(" ")[1];
                empToManagerMap.put(b, a);
            } else {
                // After enter symbol last line will be empty
                break;
            }
        }

        findCommonManger(empToManagerMap, personA, personB);
    }

    public static void findCommonManger(Map<String, String> empToManagerMap, String personA , String personB){

        if(empToManagerMap.containsKey(personA) && empToManagerMap.get(personA).equals(personB)){
            System.out.println( personB);
            return;
        }else if(empToManagerMap.containsKey(personB) && empToManagerMap.get(personB).equals(personA)){
            System.out.println(personA);
            return;
        }

        List<String> personAParentChain = new ArrayList<>();

        List<String> personBParentChain = new ArrayList<>();

        buildParentChain(empToManagerMap, personA, personAParentChain);
        buildParentChain(empToManagerMap, personB, personBParentChain);
        int lenA = personAParentChain.size();
        int lenB = personBParentChain.size();
        List<String> shortList;
        List<String> longList;
        if(lenA > lenB){
            longList = personAParentChain;
            shortList = personBParentChain;
        }else {
            longList = personBParentChain;
            shortList = personAParentChain;
        }

        int diff = longList.size() - shortList.size();
        int i = diff;
        int j = 0;
        int k = 0;
        while(k < shortList.size()){
            if(longList.get(i).equals(shortList.get(j))){
                System.out.println(longList.get(i));
                break;
            }
            i++;
            j++;
            k++;
        }


    }
    public static void buildParentChain(Map<String, String> empToManagerMap, String employee, List<String> list){
        if(employee == null){
            return;
        }
        while(employee != null){
            list.add(employee);
            String manager = empToManagerMap.get(employee);
            employee = manager;
        }
    }

    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        int _count;
        _count = Integer.parseInt(in.nextLine());

        OutputCommonManager(_count, in);
    }
}
