package Q4_07_Build_Order.DFS;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	private ArrayList<Project> nodes = new ArrayList<>();
	private HashMap<String, Project> map = new HashMap<>();
	
	public Project getOrCreateNode(String name) {
		if (!map.containsKey(name)) {
			Project node = new Project(name);
			nodes.add(node);
			map.put(name, node);
		}
		
		return map.get(name);
	}
	
	public void addEdge(String startName, String endName) {
		Project start = getOrCreateNode(startName);
		Project end = getOrCreateNode(endName);
		start.addNeighbor(end);
	}
	
	public ArrayList<Project> getNodes() {
		return nodes;
	}
}
