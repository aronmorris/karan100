package com.aronmorris.graph;
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
		int id = 0;
		for (Node n : nodes) {
			n.setID(id);
			id += 1;
		}
		
		LinkedList<Node> nLL = new LinkedList<Node>();
		
		nLL.addAll(nodes);
		
		int copiesOfN = 0;
		for (Node n : nodes) {
			for (Node m : nodes) {
				if (n.X() == m.Y() && n.Y() == m.X() && n.getID() != m.getID()) {
					//System.out.println("copy");
					int reflects = 0;
					for (int i = 0; i < nLL.size(); i++) {
						if (nLL.get(i).equals(m)) {
							reflects += 1;
						}
					}
					if (reflects > 2) {
						nLL.remove(m);
						//System.out.println("Removed " + m.toString());
					}
				}	
			}
			copiesOfN = 0;
		}
		
		this.nodes.addAll(nLL);
		
		LinkedList<Link> existsInSet = new LinkedList<Link>();
		LinkedList<Link> matches = new LinkedList<Link>();
	
		for (Node n : this.nodes) {
			
			for (Node m : this.nodes) {
				//add new link to the list if the nodes aren't the same node and share X or Y, and if the graph doesn't contain the node's reverse already
				//(1, 2):(2, 1) and (2, 1):(1, 2) doesn't mean anything in an undirected graph like this
				if (!(n.equals(m)) && (!(existsInSet.contains(new Link(n, m))) && !(existsInSet.contains(new Link(m, n))))) {
					if (n.X() == m.X() || n.Y() == m.Y()) {
						this.links.add(new Link(n, m));
						existsInSet.add(new Link(n, m));	
					}
				}
				//reflective case - add the reflective link only once
				else if (n.equals(m) && (!(existsInSet.contains(new Link(n, m))) && !(existsInSet.contains(new Link(m, n))))) {
					if (n.X() == m.Y() && n.Y() == m.X() && !(matches.contains(new Link(n, m))) && n.getID() != m.getID()) {
						//System.out.println(new Link(n, m).toString()); //debugging
						matches.add(new Link(n, m));
						this.links.add(new Link(n, m));
						nLL.remove(m);
						//this.nodes.remove(m); //doesnt work unless iterators are used and iterator use is buggy with the Node object
					}
				}
			}	
		}
		this.nodes.clear();
		this.nodes.addAll(nLL);
	}
	
	
	
	public boolean hasLinks() {
		if (this.links.size() == 0) {
			return false;
		}
		else  {
			return true;
		}
	}
	
	public Node getNodeByID(int id) {
		for (Node n : this.nodes) {
			if (n.getID() == id) {
				return n;
			}
		}
		return null;
	}
	
	
	public int getDegreeOfNode(Node node) {
		int linksToOtherNodes = 0;
	
		LinkedHashSet<Link> temp = this.links;
		
		for (Link l : temp) {
			if (l.A.equals(node) && l.B.equals(node)) {
				linksToOtherNodes += 2; //+2 if the node is reflective
				//System.out.println("Match!"); //debugging
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
	
	public LinkedHashSet<Node> getNodes() {
		return this.nodes;
	}
	
	public LinkedHashSet<Link> getLinks() {
		return this.links;
	}
	
	@Override
	public String toString() {
		String retStr = "";
		
		Node[] nArr = new Node[this.nodes.size()];
		this.nodes.toArray(nArr);
		
		Link[] lArr = new Link[this.links.size()];
		this.links.toArray(lArr);
		
		retStr += "Nodes: ";
		for (int i = 0; i < nArr.length; i++) {
			if (i % 3 == 0) { //neaten the output so it doesn't have an endless one-line string
				retStr += "\n";
			}
			if (i == nArr.length - 1) { //last item in set
				retStr += nArr[i].toString();
			}
			else {
				retStr += nArr[i].toString() + ", ";
			}
		}
		retStr += "\nLinks:\n";
		for (int i = 0; i < lArr.length; i++) {
			/*
			if (i % 3 == 0) {
				retStr += "\n";
			}
			*/
			if (i == lArr.length -1) {
				retStr += lArr[i].toString();
			}
			else {
				retStr += lArr[i].toString()+ ", \n";
			}
		}
		
		return retStr;
	}
	
	
	public class Link {
		
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
