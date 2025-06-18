package com.coderunner;

import java.util.*;

class FileSystem {

    private static class TrieNode {
        Map<String, TrieNode> children;
        int value;
        boolean isEnd;

        TrieNode() {
            children = new HashMap<>();
            value = -1;
            isEnd = false;
        }
    }

    private final TrieNode root;

    public FileSystem() {
        root = new TrieNode();
    }

    public boolean createPath(String path, int value) {
        if (!isValidPath(path)) return false;

        List<String> parts = splitPath(path);
        TrieNode current = root;

        for (int i = 0; i < parts.size(); i++) {
            String part = parts.get(i);

            if (i == parts.size() - 1) {
                if (current.children.containsKey(part)) {
                    return false;
                }
                TrieNode newNode = new TrieNode();
                newNode.value = value;
                newNode.isEnd = true;
                current.children.put(part, newNode);
                return true;
            }

            if (!current.children.containsKey(part)) {
                return false;
            }
            current = current.children.get(part);
        }

        return false;
    }

    private List<String> splitPath(String path) {
        List<String> parts = new ArrayList<>();
        String[] split = path.split("/");
        for (String part : split) {
            if (!part.isEmpty()) {
                parts.add(part);
            }
        }
        return parts;
    }

    private boolean isValidPath(String path) {
        return path != null && !path.isEmpty() && path.charAt(0) == '/' && !"/".equals(path);
    }

    public int get(String path) {
        if (!isValidPath(path)) return -1;

        List<String> parts = splitPath(path);
        return getHelper(root, 0, parts);
    }

    private int getHelper(TrieNode node, int index, List<String> parts) {
        if (index == parts.size()) {
            // If we've traversed all parts of the path, check if it's a valid endpoint
            return node.isEnd ? node.value : -1;
        }

        String part = parts.get(index);

        // Check for an exact match
        if (node.children.containsKey(part)) {
            int result = getHelper(node.children.get(part), index + 1, parts);
            if (result != -1) {
                return result;
            }
        }

        // Check for wildcard match
        if (node.children.containsKey("*")) {
            TrieNode wildcardNode = node.children.get("*");

            // Try advancing the path index to match the wildcard with multiple levels
            for (int i = index; i <= parts.size(); i++) {
                int result = getHelper(wildcardNode, i, parts);
                if (result != -1) {
                    return result;
                }
            }
        }

        // No valid match found
        return -1;
    }


    /*private int getHelperDP(TrieNode node, int index, List<String> parts) {
        int[][] dp = new int[parts.size() + 1][2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return dpHelper(node, index, parts, dp, false);
    }

    private int dpHelper(TrieNode node, int index, List<String> parts, int[][] dp, boolean inWildcard) {
        if (index == parts.size()) {
            return node.isEnd ? node.value : -1;
        }

        if (dp[index][inWildcard ? 1 : 0] != -1) {
            return dp[index][inWildcard ? 1 : 0];
        }

        int result = -1;
        String part = parts.get(index);

        // Try exact match
        if (node.children.containsKey(part)) {
            result = dpHelper(node.children.get(part), index + 1, parts, dp, false);
        }

        // Try wildcard match
        if (result == -1 && node.children.containsKey("*")) {
            TrieNode wildcardNode = node.children.get("*");

            if (index == parts.size() - 1 && wildcardNode.isEnd) {
                result = wildcardNode.value;
            } else {
                for (int j = index + 1; j < parts.size(); j++) {
                    result = dpHelper(wildcardNode, j, parts, dp, true);
                    if (result != -1) {
                        break;
                    }
                    result = dpHelper(node, j, parts, dp, false);
                    if (result != -1) {
                        break;
                    }
                }
            }
        }

        dp[index][inWildcard ? 1 : 0] = result;
        return result;
    }*/

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();

        System.out.println(fs.createPath("/a", 1)); // true
        System.out.println(fs.createPath("/a/*", 500)); // true
        System.out.println(fs.createPath("/a/*/*", 600)); // true
        System.out.println(fs.createPath("/a/*/b", 100)); // true
        System.out.println(fs.get("/a/c/d/b")); //100

        System.out.println(fs.get("/a")); // 1

        System.out.println(fs.createPath("/leet", 1)); // true
        System.out.println(fs.createPath("/leet/code", 2)); // true
        System.out.println(fs.get("/leet/code")); // 2
        System.out.println(fs.createPath("/c/d", 1)); // false
        System.out.println(fs.get("/c")); // -1

        // Wildcard tests
        System.out.println(fs.get("/a/x/y/b")); // 100
        System.out.println(fs.get("/a/abc/b")); // 100
        System.out.println(fs.get("/a/b")); // 500
        System.out.println(fs.get("/a/b/c")); // 600
    }
}
