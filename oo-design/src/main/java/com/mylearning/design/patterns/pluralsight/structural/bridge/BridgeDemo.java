package com.mylearning.design.patterns.pluralsight.structural.bridge;

public class BridgeDemo {

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setClassification("Action");
        movie.setTitle("John Wick");
        movie.setRuntime("2:15");
        movie.setYear("2014");

        Formatter printFormatter = new PrintFormatter();
        Formatter htmlFormatter = new HtmlFormatter();

        Printer moviePrinter = new MoviePrinter(movie);

        String printedMaterial = moviePrinter.print(printFormatter);
        System.out.println(printedMaterial);

        printedMaterial = moviePrinter.print(htmlFormatter);
        System.out.println(printedMaterial);
    }
}
