package com.mylearning.string;

/*
For example, for the string is "615", b1 =7 and b2 = 13, then the integer value,
expressed in decimal, is 306. The least significant digit of the result is 306 mod 13 = 7,
and we continue with 306/13 = 23. The next digit is 23 mod 13 = 10, which we denote by 'A'.
We continue with 23/13 = 1. Since 1 mod 13 = 1 and 1/13 = 0, the final digit is 1,
and the overall result is "1A7".
 */
public class BaseConversion {

    public String convertBase(String numAsString, int b1, int b2) {
        //First we will convert to base b1 to base 10 i.e. decimal and then convert to b2
        boolean isNegative = numAsString.startsWith("-");
        int numAsInt = 0;
        for (int i = (isNegative ? 1 : 0); i < numAsString.length(); i++) {
            numAsInt = numAsInt * b1; //0, b1, b1*b1, b1*b1*b1
            numAsInt += Character.isDigit(numAsString.charAt(i)) ? (numAsString.charAt(i) - '0') : (numAsString.charAt(i) - 'A' + 10);
        }

        return (isNegative ? "-" : "") + constructFromBase(numAsInt, b2);
    }

    private String constructFromBase(int numAsInt, int base){
        return numAsInt == 0 ? "" : constructFromBase(numAsInt / base, base) + (char)(numAsInt % base >= 10 ? 'A' + (numAsInt % base - 10) : '0' + numAsInt % base);
    }

    public static void main(String[] args) {
        BaseConversion baseConversion = new BaseConversion();
        System.out.println(baseConversion.convertBase("615", 7, 13));
    }
}
