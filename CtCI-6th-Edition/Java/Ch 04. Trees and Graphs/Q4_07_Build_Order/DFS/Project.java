package Q4_07_Build_Order.DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Project {
	public enum State {COMPLETE, PARTIAL, BLANK};
	private Set<Project> children = new HashSet<>();
	private String name;
	private State state = State.BLANK;
	
	public Project(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}
	
	public void addNeighbor(Project node) {
		if(!children.contains(node)) {
			children.add(node);
		}
	}
	
	public State getState() { 
		return state;
	}
	
	public void setState(State st) {
		state = st;
	}
	
	public ArrayList<Project> getChildren() {
		return new ArrayList<>(children);
	}
}
