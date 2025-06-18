package com.coderunner;

import java.util.HashMap;
import java.util.Map;

class Router {

    class PathNode {
        private Map<String, PathNode> children;
        private String value;

        PathNode() {
            children = new HashMap<>();
        }
    }

    private PathNode root;

    Router() {
        root = new PathNode();
        root.children.put("", new PathNode());
    }

    public void addRoute(String path, String result) {
        PathNode cur = root;
        String[] folders = path.split("/");
        for (String folder : folders) {
            if (!cur.children.containsKey(folder)) {
                cur.children.put(folder, new PathNode());
            }
            cur = cur.children.get(folder);
        }
        cur.value = result;
    }

    public String callRoute(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }
        return helper(path.split("/"), 0, root);
    }

    private String helper(String[] path, int idx, PathNode cur) {
        if (idx == path.length) {
            return cur.value ==  null ? "" : cur.value;
        }
        if (cur == null) {
            return "";
        }
        String folder = path[idx];
        if (folder.equals("*")) {
            for (String nextFolder : cur.children.keySet()) {
                return helper(path, idx + 1, cur.children.get(nextFolder));
            }
            return "";
        } else {
            return helper(path, idx + 1, cur.children.get(folder));
        }
    }

    public static void main(String[] args) {
        Router router = new Router();
        router.addRoute("/a", "1");
        router.addRoute("/a/*", "500");
        router.addRoute("/a/*/*", "600");
        router.addRoute("/a/*/b", "100");
        System.out.println(router.callRoute("/a/c/d/b")); //100

        System.out.println(router.callRoute("/a")); // 1

        router.addRoute("/leet", "1");
        router.addRoute("/leet/code", "2");
        System.out.println(router.callRoute("/leet/code")); // 2
        router.addRoute("/c/d", "1");
        System.out.println(router.callRoute("/c")); // -1

        // Wildcard tests
        System.out.println(router.callRoute("/a/x/y/b")); // 100
        System.out.println(router.callRoute("/a/abc/b")); // 100
        System.out.println(router.callRoute("/a/b")); // 500
        System.out.println(router.callRoute("/a/b/c")); // 600
    }
}