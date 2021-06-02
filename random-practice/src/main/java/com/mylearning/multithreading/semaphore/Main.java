package com.mylearning.multithreading.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
https://www.concretepage.com/java/java-counting-and-binary-semaphore-tutorial-with-example#counting-semaphore
Counting Semaphore is used to restrict the use of resources. Counting Semaphore plays just on the basis of number which is
subtracted and added. When the permit is required, number is subtracted by one and when released, it is added by one.
For the Counting Semaphore demo, we are creating a library of books. One book can be issued to only one reader at a time.
The other readers can get books only when the more books are available or the books are returned back to library by the readers
who has already issued. The number of readers is more than the number of books in our scenario. What all we want is issue the
books to readers if available otherwise hold on till the books are returned back to the library.
Semaphore guarantees that no two threads can access same resource at a time. If threads are more than the number of resources, they will be queued.
*/
public class Main {
    public static void main(String[] args) {
        final int threadCount = 6;
        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        final Library library = new Library();
        for(int i=0; i < threadCount; i++) {
            Reader reader = new Reader(library); //Runnable task to issue, read, return book
            executorService.execute(reader);
        }
        executorService.shutdown();
    }
}
