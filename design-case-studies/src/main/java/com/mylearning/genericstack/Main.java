package com.mylearning.genericstack;

public class Main {
    public static void main(String[] args) {
        String word = "Hello World!";
        Stack <Character>stack = new StackArray<>(word.length());

        for(int i = 0; i < word.length(); i++) {
            stack.push(word.toCharArray()[i]);
        }

        String reversedWord = "";
        while(!stack.isEmpty()) {
            char ch = (char) stack.pop();
            reversedWord += ch;
        }
        System.out.println(reversedWord);

    }
}
