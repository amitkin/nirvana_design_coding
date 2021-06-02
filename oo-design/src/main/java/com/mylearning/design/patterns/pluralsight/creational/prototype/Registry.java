package com.mylearning.design.patterns.pluralsight.creational.prototype;

import java.util.HashMap;
import java.util.Map;

public class Registry {
    public static double MOVIE_DEFAULT_PRICE = 2.99;

    private Map<String, Item> itemsMap = new HashMap<>();

    public Registry() {
        loadItems();
    }

    public Item createItem(String type) {
        Item item = null;

        try {
            item = (Item)itemsMap.get(type);
            if (item instanceof Movie) {
                item = (Movie) item.clone();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return item;
    }

    private void loadItems() {
        Movie movie = new Movie();
        movie.setRuntime("2 hours");
        movie.setPrice(MOVIE_DEFAULT_PRICE);
        movie.setTitle("coolio");
        movie.setUrl("https://coolio.movie.com");
        movie.getProducer().setName("homer");
        itemsMap.put("movie", movie);

        Book book = new Book();
        book.setNumberOfPages(5);
        book.setPrice(1.99);
        book.setTitle("cat in hat");
        book.setUrl("https://catinhat.books.com");
        itemsMap.put("book", book);
    }
}
