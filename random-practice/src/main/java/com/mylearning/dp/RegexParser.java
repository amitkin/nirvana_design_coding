package com.mylearning.dp;

//https://leetcode.com/problems/regular-expression-matching
class RegexParser {

    static boolean isMatch(String s, String p) {
        //return isMatchHelper(s, p, 0, 0);
        return isMatchDP(s, p);
    }

    /*
    1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
    2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
    3, If p.charAt(j) == '*':
       here are two sub conditions:
                   1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
                   2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                                  dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
                               or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                               or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
    */
    static boolean isMatchDP(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 2; i < n+1; i++) {
            if (p.charAt(i-1) == '*' && dp[0][i-2]) {
                dp[0][i] = true;
            }
        }
        for(int i = 1; i < m + 1; i++){
            for(int j = 1; j < n + 1; j++){
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if(curS == curP) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if(curP == '.'){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if(curP == '*'){
                    char preCurP = p.charAt(j - 2);
                    if(preCurP != curS && preCurP != '.'){
                        dp[i][j] = dp[i][j - 2];
                    }else{
                        dp[i][j] = (dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 1][j]);
                    }
                }
            }
        }
        return dp[m][n];
    }

    static boolean isMatchHelper(String text, String pattern, int textIndex, int patIndex) {
        //base cases - one of the indexes reached the end of text or pattern
        if (textIndex >= text.length()) {
            if (patIndex >= pattern.length()){
                return true;
            } else {
                if ((patIndex+1 < pattern.length()) &&  (pattern.charAt(patIndex+1) == '*')) {
                    return isMatchHelper(text, pattern, textIndex, patIndex + 2); //"abaa", "a.*a*", 4, 3
                } else {
                    return false;
                }
            }
        } else if ((patIndex >= pattern.length()) && (textIndex < text.length())) {
            return false;
        } else if ((patIndex+1 < pattern.length()) && (pattern.charAt(patIndex+1) == '*')) { //string matching for character followed by '*'
            if ((pattern.charAt(patIndex) == '.') || (text.charAt(textIndex) == pattern.charAt(patIndex))) {
                return isMatchHelper(text, pattern, textIndex, patIndex + 2) || isMatchHelper(text, pattern, textIndex + 1, patIndex);
            } else {
                return isMatchHelper(text, pattern, textIndex, patIndex + 2);
            }
        } else if ((pattern.charAt(patIndex) == '.') || (pattern.charAt(patIndex) == text.charAt(textIndex))) { //string matching for '.' or ordinary char.
            return  isMatchHelper(text, pattern, textIndex + 1, patIndex + 1);
        } else {
            return false;
        }
    }

    static boolean isMatchHelper1(String text, String pattern, int i, int j) {

        if(i >= text.length() && j >= pattern.length()) {
            return true;
        }

        if(j >= pattern.length()) {
            //i < text.length()
            return false;
        }

        if(i >= text.length()) {
            //j < pattern.length()
            return true;
        }

        int starCharPosition = -1;
        if(j+1 < pattern.length() && pattern.charAt(j+1) == '*') {
            starCharPosition = j;
        }

        if(text.charAt(i) == pattern.charAt(j) && starCharPosition == -1) {
            //abc a.c
            return isMatchHelper1(text, pattern, i+1, j+1);
        } else {
            if(starCharPosition >= 0) {
                if(text.charAt(i) != pattern.charAt(starCharPosition)) {
                    //cd  .*c
                    if(pattern.charAt(starCharPosition) == '.') {
                        //first call for zero times - bcd  c.
                        //second call for 1 time match - cd, b*c.
                        return isMatchHelper1(text, pattern, i,j+2) || isMatchHelper1(text, pattern, i+1, j);
                    }
                    //cd  b*c.
                    return isMatchHelper1(text, pattern,i, j+2);
                } else {
                    //bcd  b*c.
                    //first call for zero times - bcd  c.
                    //second call for 1 time match - cd, b*c.
                    return isMatchHelper1(text, pattern, i,j+2) || isMatchHelper1(text, pattern, i+1, j);
                }
            }
            if(pattern.charAt(j) == '.') {
                return isMatchHelper1(text, pattern,i+1, j+1);
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "aa"));
        System.out.println(isMatch("abc", "a.c"));
        System.out.println(isMatch("abbb", "ab*"));
        System.out.println(isMatch("acd", "ab*c."));
        System.out.println(isMatch("abaa", "a.*a*"));
        System.out.println(isMatch("", "a*b*"));
        System.out.println(isMatch("", "*"));
    }
}
