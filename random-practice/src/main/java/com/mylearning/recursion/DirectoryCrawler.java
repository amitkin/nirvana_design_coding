package com.mylearning.recursion;

import java.io.File;

public class DirectoryCrawler {

    void crawl(String filename){
        crawl(filename, "");
    }

    void crawl(String filename, String indent){
        File f = new File(filename);
        System.out.println(indent + f.getName());
        if(f.isFile()) {
            //System.out.println(indent + f.getName());
        } else {
            String[] filenames = f.list();

            //Iterative part of the problem is solved using loops and similar part using recursion
            //so we use for loop here
            for(String innerFilename: filenames) {
                crawl(filename + innerFilename + "/", indent + "    ");
            }
        }
    }

    public static void main(String[] args) {
        DirectoryCrawler c = new DirectoryCrawler();
        c.crawl("/");
    }
}
