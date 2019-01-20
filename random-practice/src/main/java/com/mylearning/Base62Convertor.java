package com.mylearning;

public class Base62Convertor {

    public String base62Encode(long decimal){
        String s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String hashStr = "";
        while(decimal > 0){
            hashStr = s.charAt((int)decimal % 62) + hashStr;
            decimal = decimal / 62;
        }
        return hashStr;
    }

    public static void main(String[] args) {
        Base62Convertor convertor = new Base62Convertor();
        //why it is not accomodating one more zero after changing the type to long
        System.out.println(convertor.base62Encode(1000000000));
    }
}
