package com.aronmorris.graph;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;


public class Graph {
	
	
	private LinkedList<Node> nodes;

	private LinkedList<Link> links;
	
	public Graph() {
		this.nodes = new LinkedList<Node>();
		this.links = new LinkedList<Link>();
	}
	
	public void addNode(int x, int y) {
		Node n = new Node(x, y);
		
		for (Node m : this.nodes) {
			if (n.equals(m)) {
				m.setReflective(true);
				n.setReflective(true);
				if (!this.links.contains(new Link(n, m))) {
					this.links.add(new Link(n, m));
				}
			}
		}
		if (!n.isReflective()) {
			this.nodes.add(n);
			addLink();
			
		}
		
	}
	
	private void addLink() {
		for (Node n : this.nodes) {
			for (Node m : this.nodes) {
				
				if (m.isReflective() && !this.links.contains(new Link(m, m))) {
					this.links.add(new Link(m, m));
				}
				
				if (!n.equals(m) && (n.X == m.X || n.Y == m.Y) && !(this.links.contains(new Link(n, m)) || this.links.contains(new Link(m, n)))) {
					this.links.add(new Link(n, m));
				}
			}
		}
		
	}
	
	public void removeNode(int x, int y) {
		for (Iterator<Node> it = this.nodes.iterator(); it.hasNext();) {
			Node n = it.next();
			if (n.X == x && n.Y == y) {
				it.remove();
				for (int i = 0; i < this.links.size(); i++) {
					if (links.get(i).A.equals(n) || links.get(i).B.equals(n)) {
						links.remove(i);
					}
				}
			}
		}
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
	
		//TODO reimplement
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
		
		retStr += "Nodes: \n";
		int ctr = 0;
		for (Iterator<Node> it = this.nodes.iterator(); it.hasNext();) {
			ctr += 1;
			retStr += it.next() + (it.hasNext() ? ", " : "");
			if (ctr % 4 == 0) {
				retStr += "\n";
			}
		}
	
		retStr += "\nLinks: \n";
		for (Iterator<Link> it = this.links.iterator(); it.hasNext();) {
			retStr += it.next() + (it.hasNext() ? ", \n" : "\n");	
		}
		return retStr;
	}
	
	private final class Node {
		private final int X, Y;
		
		private boolean reflective;
		
		protected Node(int a, int b) {
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
		
		protected void setReflective(boolean b) {
			this.reflective = b;
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
