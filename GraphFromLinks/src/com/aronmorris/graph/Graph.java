package com.aronmorris.graph;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;


public class Graph {
	
	
	private LinkedList<Node> nodes;

	private LinkedHashSet<Link> links;
	
	public Graph() {
		this.nodes = new LinkedList<Node>();
		this.links = new LinkedHashSet<Link>();
	}
	
	public boolean hasLinks() {
		if (this.links.size() == 0) {
			return false;
		}
		else  {
			return true;
		}
	}
	
	public int getDegreeOfNode(Node node) {
		int linksToOtherNodes = 0;
	
		
		return linksToOtherNodes;
	}
	
	public LinkedHashSet<Link> getLinksOfNode(Node node) {
		LinkedHashSet<Link> returnSet = new LinkedHashSet<Link>();
		
		for (Link l : this.links) {
			if (l.A.equals(node) || l.B.equals(node)) {
				returnSet.add(l);
			}
		}
		
		return returnSet;
	}
	
	
	@Override
	public String toString() {
		String retStr = "";
		
		//TODO finish reimplementation
		
		return retStr;
	}
	
	private final class Node {
		private final int X, Y;
		
		private boolean reflective;
		
		protected Node(int a, int b, boolean reflective) {
			this.X = a;
			this.Y = b;
			
		}
		@Override
		public String toString() {
			return "(" + this.X + ", " + this.Y + ")";
		}
		
		@Override
		public boolean equals(Object n) {
			if (n == null) {
				return false;
			}
			if (n == this) {
				return true;
			}
			if (!(n instanceof Link)) {
				return false;
			}
			
			Node temp = (Node) n;
			
			if (this.X == temp.X && this.Y == temp.Y) {
				return true;
			}
			return false;
		}
		
		protected int X() {
			return this.X;
		}
		
		protected int Y() {
			return this.Y;
		}
		
		protected boolean isReflective() {
			return this.reflective;
		}
		
	}
	
	private class Link {
		
		private Node A, B;
		
		protected Link(Node a, Node b) {
			this.A = a;
			this.B = b;
		}
		
		public Node A() {
			return this.A;
		}
		
		public Node B() {
			return this.B;
		}
		
		@Override
		public String toString() {
			return "[" + this.A.toString() + ", " + this.B.toString() + "]";
		}
		
		@Override
		public boolean equals(Object l) {
			if (l == null) {
				return false;
			}
			if (l == this) {
				return true;
			}
			if (!(l instanceof Link)) {
				return false;
			}
			
			Link temp = (Link) l;
			
			if (temp.A.X() == this.A.X() && temp.B.X() == this.B.X() && temp.A.Y() == this.A.Y() && temp.B.Y() == this.B.Y()) {
				return true;
			}
			return false;
		}
		
	}

}
