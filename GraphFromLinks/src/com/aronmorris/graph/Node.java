package com.aronmorris.graph;
/**
 * Node is a class which defines a node in 2 dimensions with an X and Y coordinate
 * Used as a singular component of a graph
 * @author Aron
 *
 */
public final class Node {

	private int X, Y;
	
	private int id; //optional field to keep track of which node is which
	
	public Node() {
		this.X = 0;
		this.Y = 0;
	}
	
	public Node(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	public int X() {
		return this.X;
	}
	
	public int Y() {
		return this.Y;
	}
	
	public void setID(int i) {
		this.id = i;
	}
	
	public int getID() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return "(" + this.X + ", " + this.Y + ")";
	}
	
	@Override
	/* Node ID isn't factored in here because IDs are unique per node
	 * This method only compares the node values as set by the user
	 */
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o == this) {
			return true;
		}
		if (!(o instanceof Node)) {
			return false;
		}
		
		Node temp = (Node) o;
		
		if (temp.X == this.X && temp.Y == this.Y) {
			return true;
		}
		
		return false;
	}
	
}
