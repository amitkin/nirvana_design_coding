package com.mylearning.design;

/*
Directory data structure contains a unified files hashmap, which contains the list of all the files and subdirectories
in the current directory. Apart from this, we contain an entry isfile, which when True indicates that the current files
entry is actually corresponding to a file, otherwise it represents a directory. Further, since we are considering the
directory and files entries in the same manner, we need an entry for content, which contains the contents of the current
file(if isfile entry is True in the current case). For entries corresponding to directories, the content field is kept empty.

TreeMap can be used instead of HashMap to maintain lexicographic ordering

The advantage of this scheme of maintaining the directory structure is that it is expandable to include
even more commands easily. For example, rmdir to remove a directory given an input directory path. We need
to simply reach to the destined directory level and remove the corresponding directory entry from the
corresponding dirsdirsdirs keys.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystem {
    class File {
        boolean isFile = false;
        Map<String, File> children = new HashMap<>();
        String content = "";
    }

    File root = null;

    public FileSystem() {
        root = new File();
    }

    //Time : O(m+n+klog(k))
    //m - length of input string, n refers to the depth of the last directory level in the given input for ls
    //k refers to the number of entries(files+subdirectories) in the last level directory(in the current input).
    public List<String> ls(String path) {
        String[] dirs = path.split("/");
        File node = root;
        List<String> result = new ArrayList<>();
        String name = "";
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.children.containsKey(dir)) {
                return result;
            }
            node = node.children.get(dir);
            name = dir;
        }

        if (node.isFile) {
            result.add(name);
        }
        else {
            for (String key : node.children.keySet()) {
                result.add(key);
            }
        }

        Collections.sort(result);

        return result;
    }

    //O(m+n)
    public void mkdir(String path) {
        String[] dirs = path.split("/");
        File node = root;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.children.containsKey(dir)) {
                File file = new File();
                node.children.put(dir, file);
            }
            node = node.children.get(dir);
        }
    }

    //O(m+n)
    public void addContentToFile(String filePath, String content) {
        String[] dirs = filePath.split("/");
        File node = root;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.children.containsKey(dir)) {
                File file = new File();
                node.children.put(dir, file);
            }
            node = node.children.get(dir);
        }
        node.isFile = true;
        node.content += content;
    }

    //O(m+n)
    public String readContentFromFile(String filePath) {
        String[] dirs = filePath.split("/");
        File node = root;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.children.containsKey(dir)) {
                File file = new File();
                node.children.put(dir, file);
            }
            node = node.children.get(dir);
        }

        return node.content;
    }

    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        fileSystem.ls("");
    }
}
