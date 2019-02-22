package com.mylearning.ctci.filesystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FileSystem {

    Directory root;

    HashMap<String, Directory> dirs = new HashMap<>();

    List<String> linkedList = new LinkedList<>();

    public FileSystem() {
        this.root = new Directory("root", null);
        dirs.put("/", root);
        linkedList.add("root");
    }

    void createFile(String filename){
        String currentDirectory = getCurrentDirectory();
        Directory dir = dirs.get(currentDirectory);
        dir.addEntry(new File(filename, dir));
    }

    boolean makeDirectory(String directoryName){
        String currentDirectory = getCurrentDirectory();
        Directory dir = dirs.get(currentDirectory);
        dir.addEntry(new Directory(directoryName, dir));
        dirs.put(currentDirectory.concat(directoryName).concat("/"),dir);
        return true;
    }

    boolean changeDirectory(String path){
        if("..".equals(path)){
            if(linkedList.size() <=1) {
                return false;
            }

            linkedList.remove(linkedList.size()-1);
            return true;
        }else {
            String directoryPath =  getCurrentDirectory().concat(path);
            if(!dirs.containsKey(directoryPath)){
                return false;
            }
            linkedList.add(path);
            return true;
        }
    }

    public List<Entry> listContents(){
        String currentDirectory = getCurrentDirectory();
        Directory dir = dirs.get(currentDirectory);
        return dir.contents;
    }
    String getCurrentDirectory(){
        String currentPath = String.join("/", linkedList).concat("/");
        int index = currentPath.indexOf("/");
        currentPath = currentPath.substring(index);
        return currentPath;
    }
}
