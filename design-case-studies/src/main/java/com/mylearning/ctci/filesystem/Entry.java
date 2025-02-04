package com.mylearning.ctci.filesystem;

public abstract class Entry {
	protected Directory parent;
	protected long created;
	protected long lastUpdated;
	protected long lastAccessed;
	protected String name;
	
	public Entry(String n, Directory p) {
		name = n;
		parent = p;
		created = System.currentTimeMillis();
	}
	
	public boolean delete() {
		if (parent == null) {
			return false;
		}
		return parent.deleteEntry(this);
	}
	
	public String getFullPath() {
		if (parent == null) {
			return name;
		} else {
			return parent.getFullPath() + "/" + name;
		}
	}
	
	public long getCreationTime() {
		return created;
	}
	
	public long getLastUpdatedTime() {
		return lastUpdated;
	}
	
	public long getLastAccessedTime() {
		return lastAccessed;
	}
	
	public void changeName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}

	@Override public String toString() {
		return getName();
	}
}
