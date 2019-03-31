package Q4_07_Build_Order.EdgeRemoval;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Project {
	private Set<Project> children = new HashSet<>();
	private String name;
	private int dependencies = 0;
	
	public Project(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}
	
	public void addNeighbor(Project node) {
		if(!children.contains(node)) {
			children.add(node);
			node.incrementDependencies();
		}
	}
	
	public void incrementDependencies() {
		dependencies++;
	}
	
	public ArrayList<Project> getChildren() {
		return new ArrayList<>(children);
	}
	
	public void decrementDependencies() {
		dependencies--;
	}
	
	public int getNumberDependencies() {
		return dependencies;
	}
}
