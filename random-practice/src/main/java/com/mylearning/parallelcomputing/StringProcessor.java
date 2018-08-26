package com.mylearning.parallelcomputing;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
1. Parameter is CircularQueueDynamicSizing which has got Strings
2. Output should be strings each containing first element as length of string, second element as first character and
third element onwards should be the string after removing vowels
3. Separate thread for each task and result should be compiled from all these threads
4. In one program have one inputQueue and on that inputQueue keep on writing strings on inputQueue
5. In another program will process the inputQueue, it will take string, get the output
6. It should be non-blocking
*/
/**
 * Created by Amit Kumar on 21-Mar-18.
 */
public class StringProcessor {

    private final Queue<String> inputQueue = new ConcurrentLinkedQueue<>();
    private final ExecutorService mainPool;
    private final ExecutorService innerPool;


    private StringProcessor(ExecutorService mainPool, ExecutorService innerPool){
        this.mainPool = mainPool;
        this.innerPool = innerPool;
    }

    //Producer to generate Strings
    class StringGeneratorCallable implements Callable<Void> {

        public Void call() {
            int count = 0;
            while (count++ < 10) {
                String randomString = randomString(10);
                if(!randomString.isEmpty()) {
                    inputQueue.offer(randomString);
                } else {
                    System.out.println("Skipping null string insertion in queue");
                }
            }
            return null;
        }
    }

    //Consumer to process Strings
    class StringProcessorCallable implements Callable<Void> {

        private final ExecutorService innerPool;

        public StringProcessorCallable(ExecutorService innerPool) {
            this.innerPool = innerPool;
        }

        public Void call() throws InterruptedException {
            int count = 0;
            while (count++ < 10) {
                String stringToProcess = inputQueue.poll();

                //Invoke 3 worker threads for LengthCallable, FirstCharacterCallable and RemoveVowelsCallable
                try {
                    //Blocking calls and will consume 3 threads at for each StringProcessor thread created
                    //Since the pool for these are different so they will always get processed for infinite supply of strings in the inputQueue
                    Integer stringToProcessLength = innerPool.submit(new LengthCallable(stringToProcess)).get();
                    Character stringToProcessFirstChar = innerPool.submit(new FirstCharacterCallable(stringToProcess)).get();
                    String noVowelString = innerPool.submit(new RemoveVowelsCallable(stringToProcess)).get();

                    StringBuffer result = new StringBuffer();
                    result.append(stringToProcessLength);
                    result.append(stringToProcessFirstChar);
                    result.append(noVowelString);

                    System.out.println("Original String : " + stringToProcess + " Processed string : " + result);

                } catch (ExecutionException e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }
            return null;
        }
    }

    class LengthCallable implements Callable<Integer> {
        String str;
        private LengthCallable(String str) {
            this.str = str;
        }

        public Integer call() {
            if(str != null) {
                return str.length();
            }
            return null;
        }
    }

    class FirstCharacterCallable implements Callable<Character> {
        String str;
        private FirstCharacterCallable(String str) {
            this.str = str;
        }
        public Character call() {
            if(str != null && str.length() > 0) {
                return str.charAt(0);
            }
            return null;
        }
    }

    class RemoveVowelsCallable implements Callable<String> {

        String str;
        private RemoveVowelsCallable(String str) {
            this.str = str;
        }
        public String call() {
            if(str != null) {
                return remVowels(str);
            }
            return null;
        }
    }

    private String remVowels(String str)
    {
        return str.replaceAll("[AaEeIiOoUu]", "");
    }

    private String randomString(int count) {
        String ALPHA_STRING = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_STRING.length());
            builder.append(ALPHA_STRING.charAt(character));
        }
        return builder.toString();
    }

    private void processInput(){
        while (true) {
            mainPool.submit(new StringProcessorCallable(innerPool));
            mainPool.submit(new StringGeneratorCallable());
        }
    }

    public static void main(String[] args) {
        //Thread pool for producer and consumer threads for generating and processing the threads
        ExecutorService mainPool = Executors.newFixedThreadPool(15);

        //Thread pool for tasks performed on each string since they are blocking
        ExecutorService innerPool = Executors.newFixedThreadPool(10);

        StringProcessor stringProcessor = new StringProcessor(mainPool, innerPool);
        stringProcessor.processInput();
        mainPool.shutdown();
        innerPool.shutdown();
    }
}
