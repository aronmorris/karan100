package com.aronmorris.graph;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;


public class Graph {
	
	//set contains all the nodes in the graph
	private LinkedHashSet<Node> nodes = new LinkedHashSet<Node>();
	
	//set contains unordered pairs (Link) of nodes which have matching elements
	private LinkedHashSet<Link> links = new LinkedHashSet<Link>();
	
	public Graph(Set<Node> nodes) 
	{
		this.nodes.addAll(nodes);
		
		LinkedList<Link> existsInSet = new LinkedList<Link>();
		LinkedList<Link> matches = new LinkedList<Link>();

		for (Node n : nodes) {
			for (Node m : nodes) {
				//add new link to the list if the nodes aren't the same node and share X or Y, and if the graph doesn't contain the node's reverse already
				//(1, 2):(2, 1) and (2, 1):(1, 2) doesn't mean anything in an undirected graph like this
				if (!(n.equals(m)) && (!(existsInSet.contains(new Link(n, m))) && !(existsInSet.contains(new Link(m, n))))) {
					if (n.X() == m.X() || n.Y() == m.Y()) {
						this.links.add(new Link(n, m));
						existsInSet.add(new Link(n, m));	
					}
				}
				//reflective case
				else if (n.equals(m) && (!(existsInSet.contains(new Link(n, m))) && !(existsInSet.contains(new Link(m, n))))) {
					if (n.X() == m.Y() && n.Y() == m.X() && !(matches.contains(new Link(n, m)))) {
						System.out.println(new Link(n, m).toString());
						matches.add(new Link(n, m));
						this.links.add(new Link(n, m));
					}
				}
			}	
		}
	}
	
	
	public int getDegreeOfNode(Node node) {
		int linksToOtherNodes = 0;
	
		LinkedHashSet<Link> temp = this.links;
		
		for (Link l : temp) {
			if (l.A.equals(node) && l.B.equals(node)) {
				linksToOtherNodes += 2; //+2 if the node is reflective
				System.out.println("Match!");
			}
			else if (l.A.equals(node) || l.B.equals(node)) {
				linksToOtherNodes += 1;
			}
		}
		
		
		return linksToOtherNodes;
	}
	
	public Set<Node> getNodes() {
		return this.nodes;
	}
	
	public Set<Link> getLinks() {
		return this.links;
	}
	
	@Override
	public String toString() {
		String retStr = "";
		
		retStr += "Nodes: ";
		for (Node n : nodes) {
			retStr += n.toString() + ", ";
		}
		retStr += "\nLinks: \n";
		for (Link l : links) {
			retStr += l.toString() + ", \n";
		}
		
		return retStr;
	}
	
	//A 2-element pair, representing a link between Nodes A and B
	//This is private because while nodes are necessarily generated externally,
	//links are only a subset of the nodes and only exist in the context of a graph
	private final class Link {
		
		private Node A, B;
		
		protected Link(Node a, Node b) {
			this.A = a;
			this.B = b;
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
