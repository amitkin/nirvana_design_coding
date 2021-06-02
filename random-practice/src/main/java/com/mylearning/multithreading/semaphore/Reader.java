package com.mylearning.multithreading.semaphore;

public class Reader implements Runnable {
    private Library library;
    public Reader(Library library) {
        this.library = library;
    }

    @Override
    public void run() {
        try {
            Book book = library.issueBook();
            book.read();
            library.returnBook(book);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
