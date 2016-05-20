package com.aronmorris.graph;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * Nodes and Links are immutable and can't be modified after being set. 
 * However, the graph can add and remove them from its lists without modifying
 * any individual node or link
 * @author Aron
 *
 */
public class Graph {
	
	
	private LinkedHashSet<Node> nodes;

	private LinkedList<Link> links;
	
	public Graph() {
		this.nodes = new LinkedHashSet<Node>();
		this.links = new LinkedList<Link>();
	
	}
	
	public void addNode(int x, int y) {
		Node n = new Node(x, y);
		
		for (Node m : this.nodes) {
			if (n.equals(m)) {
				m.setReflective(true);
				n.setReflective(true);
				//System.out.println("!!!");

				if (!this.links.contains(new Link(n, m)) || !this.links.contains(new Link(m, n))) {
					this.links.add(new Link(n, m));
					System.out.println("!!!");
					
				}
				
			}
			//System.out.println("!!!");
		}
		
		if (!n.isReflective() && !this.nodes.contains(n)) {
			this.nodes.add(n);
			addLink();
		}
		
	}
	
	public LinkedHashSet<Node> getAllNodes() {
		return this.nodes;
	}
	
	public Node get(int i) {
		return (Node) this.nodes.toArray()[i];
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
				for (Iterator<Link> lit = this.links.iterator(); lit.hasNext();) {
					Link ln = lit.next();
					if (ln.A.equals(n) || ln.B.equals(n)) {
						lit.remove();
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
	
		for (Iterator<Link> it = this.links.iterator(); it.hasNext();) {
			Link l = it.next();
			if (l.A.equals(node) && l.B.equals(node)) {
				linksToOtherNodes += 2;
			}
			
			else if (l.A.equals(node) || l.B.equals(node)) {
					linksToOtherNodes += 1;
			}
				
		
			
		}
		
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
		
		private boolean reflective = false;
		
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
			if (!(n instanceof Node)) {
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
