package com.mylearning.onlinetests.prudential;

public class PalindromicSequence {

    public static int maxScore(String s){
        if(s == null || s.length() == 0){
            return  0;
        }

        return findLPSLength(s);
    }

    private static int findLPSLength(String st) {

        // dp[i][j] stores the length of LPS from index 'i' to index 'j'
        int[][] dp = new int[st.length()][st.length()];
        int n = st.length();

        // every sequence with one element is a palindrome of length 1
        for (int i = 0; i < st.length(); i++){
            dp[i][i] = 1;
        }


        for (int startIndex = st.length() - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < st.length(); endIndex++) {
                // case 1: elements at the beginning and the end are the same
                if (st.charAt(startIndex) == st.charAt(endIndex)) {
                    dp[startIndex][endIndex] = 2 + dp[startIndex + 1][endIndex - 1];
                } else { // case 2: skip one element either from the beginning or the end
                    dp[startIndex][endIndex] = Math.max(dp[startIndex + 1][endIndex], dp[startIndex][endIndex - 1]);
                }
            }
        }

        int maxProd = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n-1;j++){
                maxProd = Math.max(maxProd,dp[i][j]*dp[j+1][n-1]);
            }
        }
        return maxProd;
    }

    public static void main(String[] args) {
        System.out.println(maxScore("aacdcbdccdccbacadcdcbbbdbcaacabddbcaddbccdcaccadbadcdcdcbaabcadbdcabdbcccbddcdabcaadcdadcacbdbccccbcacccdbacbddbacbccdadddbccdbddcabbbcccdddadddccdbddabbddcaadacaacdddbcbbccdadadbdbbcaaabccabdaaddadaa"));
        System.out.println(maxScore("aabb"));
        System.out.println(maxScore("a"));
        System.out.println(maxScore("ab"));
    }
}
