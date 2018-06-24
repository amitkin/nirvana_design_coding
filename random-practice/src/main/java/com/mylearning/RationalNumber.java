package com.mylearning;

public class RationalNumber {
    private int numerator, denominator;

    public RationalNumber(int numer, int denom) {
        if (denom == 0)
            denom = 1;

        // Make the numerator "store" the sign
        if (denom < 0) {
            numer = numer * -1;
            denom = denom * -1;
        }

        numerator = numer;
        denominator = denom;

        reduce();
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public RationalNumber add(RationalNumber op2) {
        int commonDenominator = denominator * op2.getDenominator();
        int numerator1 = numerator * op2.getDenominator();
        int numerator2 = op2.getNumerator() * denominator;
        int sum = numerator1 + numerator2;

        return new RationalNumber(sum, commonDenominator);
    }

    public String toString() {
        String result;

        if (numerator == 0)
            result = "0";
        else if (denominator == 1)
            result = numerator + "";
        else
            result = numerator + "/" + denominator;

        return result;
    }

    private void reduce() {
        if (numerator != 0) {
            int common = gcd(Math.abs(numerator), denominator);

            numerator = numerator / common;
            denominator = denominator / common;
        }
    }

    private int gcd(int num1, int num2) {
        while (num1 != num2)
            if (num1 > num2)
                num1 = num1 - num2;
            else
                num2 = num2 - num1;

        return num1;
    }


    /*
    4
    4 2
    2 4
    2 4
    2 3

    output : 11 3
    */
    public static void main(String[] args) {
        String[] a = new String[]{"4 2", "2 4", "2 4", "2 3"};
        args = a;

        RationalNumber result = new RationalNumber(0, 1);
        for(int i=0; i<args.length; i++){
            String[] numbers = args[i].split(" ");
            RationalNumber r = new RationalNumber(Integer.valueOf(numbers[0]), Integer.valueOf(numbers[1]));
            result = result.add(r);
        }
        System.out.println(result);
    }
}