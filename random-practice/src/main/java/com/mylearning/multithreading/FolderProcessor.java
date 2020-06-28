package com.mylearning.multithreading;

import static java.lang.Thread.sleep;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class FolderProcessor {

    private static final Logger log = Logger.getGlobal();

    private final ExecutorService mainPool;
    private String directoryPath;

    private FolderProcessor(ExecutorService mainPool, String directoryPath){
        this.mainPool = mainPool;
        this.directoryPath = directoryPath;
    }

    private void processInput(){
        //Read the directory for all the files and create task for each of them and submit
        File directory = new File(directoryPath);
        List<File> files = new ArrayList<>();
        listFilesForFolder(directory, files);
        for(File file : files){
            mainPool.submit(new FileCallable(file));
            //mainPool.submit(processFileTask(file));
        }
    }

    // Creates a task that will process the given file path when executed.
    private class FileCallable implements Callable<String> {
        File file;

        private FileCallable(File file) {
            this.file = file;
        }

        public String call() throws Exception{
            return processFile(file);
        }
    }

    private Callable<String> processFileTask(File file) {
        return () -> processFile(file);
    }

    private String processFile(File file) throws Exception
    {
        log.info("Processing file : " + file.getName());
        sleep(5000);
        return "Success";
    }

    private void listFilesForFolder(final File directory, List<File> files) {
        if(directory != null) {
            for (final File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    listFilesForFolder(file, files);
                } else {
                    files.add(file);
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        FolderProcessor folderProcessor = new FolderProcessor(threadPool, "C:/Temp");
        folderProcessor.processInput();
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
