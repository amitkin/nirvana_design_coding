package Q4_07_Build_Order.DFS;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class WithoutOOBuildOrder {
    Map<String, Entry<Integer, String>> projectEntries = new HashMap<>();
    Map<String, Set<String>> adj = new HashMap<>();

    public String[] findBuildOrderDFS(String[] projects, String[][] dependencies) {

        for(int i =0; i< projects.length; i++) {
            projectEntries.put(projects[i], new SimpleEntry<>(i, projects[i]));
        }

        for (int i = 0; i < dependencies.length; i++) {
            Set<String> set = adj.getOrDefault(dependencies[i][0], new HashSet<>());
            set.add(dependencies[i][1]);
            adj.put(projectEntries.get(dependencies[i][0]).getValue(), set);
        }
        boolean[] visited = new boolean[projects.length];
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < projects.length; i++) {
            if (!topologicalSort(adj, projects, i, stack, visited, new boolean[projects.length])) return new String[0];
        }
        int i = 0;
        String[] result = new String[projects.length];
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();
        }
        return result;
    }

    private boolean topologicalSort(Map<String, Set<String>> adj, String[] projects, int v, Stack<String> stack, boolean[] visited, boolean[] isLoop) {
        if (visited[v]) return true;
        if (isLoop[v]) return false;
        isLoop[v] = true;
        for (String u : adj.getOrDefault(projects[v], Collections.emptySet())) {
            if (!topologicalSort(adj, projects, projectEntries.get(u).getKey(), stack, visited, isLoop)) return false;
        }
        visited[v] = true;
        stack.push(projects[v]);
        return true;
    }

    public static void main(String[] args) {
        WithoutOOBuildOrder m = new WithoutOOBuildOrder();

        String[] projects = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String[][] dependencies = {
                {"a", "b"},
                {"b", "c"},
                {"a", "c"},
                {"d", "e"},
                {"b", "d"},
                {"e", "f"},
                {"a", "f"},
                {"h", "i"},
                {"h", "j"},
                {"i", "j"},
                {"g", "j"}};

        System.out.println(Arrays.toString(m.findBuildOrderDFS(projects, dependencies)));//	[j, i, h, g, f, e, d, c, b, a]
    }
}
