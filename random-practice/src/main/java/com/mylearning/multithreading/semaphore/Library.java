package com.mylearning.multithreading.semaphore;

import java.util.concurrent.Semaphore;

/*
When we instantiate Semaphore we need to pass maximum number of permits and fairness settings. The role of fairness setting is to decide
the ordering of threads that are in queue to acquire permit by using acquire() method of Semaphore. When the fairness is true, the permit
is acquired in First-in First-out (FIFO) policy. It means the thread that request to acquire the permit first, will be given permit first
when available. The other threads will get permits in subsequent order. If the fairness settings is false, Semaphore does not give guarantee
to acquire permit in FIFO order. Passing fairness setting as an argument is optional, default is false.
*/
public class Library {
    private static final int MAX_PERMIT = 3;
    private final Semaphore availableBook = new Semaphore(MAX_PERMIT, true);
    private Book[] books = {new Book("Ramayan"), new Book("Mahabharat"), new Book("Panchtantra")};
    private boolean[] beingRead = new boolean[MAX_PERMIT];

    //Book is being issued to reader
    public Book issueBook() throws InterruptedException {
        availableBook.acquire();
        return getNextAvailableBook();
    }

    private synchronized Book getNextAvailableBook() {
        Book book = null;
        for (int i = 0; i < MAX_PERMIT; ++i) {
            if (!beingRead[i]) {
                beingRead[i] = true;
                book = books[i];
                System.out.println(book.getBookName()+" has been issued.");
                break;
            }
        }
        return book;
    }

    //Book is being returned to library
    public void returnBook(Book book) {
        if (markAsAvailableBook(book))
            availableBook.release();
    }

    private synchronized boolean markAsAvailableBook(Book book) {
        boolean flag = false;
        for (int i = 0; i < MAX_PERMIT; ++i) {
            if (book == books[i]) {
                if (beingRead[i]) {
                    beingRead[i] = false;
                    flag = true;
                    System.out.println(book.getBookName()+" has been returned.");
                }
                break;
            }
        }
        return flag;
    }
}
