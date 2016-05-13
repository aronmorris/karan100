import java.util.LinkedHashSet;
import java.util.Set;


public class Graph {
	
	//set contains all the nodes in the graph
	private Set<Node> nodes = new LinkedHashSet<Node>();
	
	//set contains unordered pairs (Link) of nodes which have matching elements
	private Set<Link> links = new LinkedHashSet<Link>();
	
	public Graph(Set<Node> nodes) 
	{
		this.nodes.addAll(nodes);
		
		for (Node n : nodes) {
			for (Node m : nodes) {
				//add new link to the list if the nodes aren't the same node and share X or Y
				if (!(n.equals(m)) && ((n.X() == m.X() || n.Y() == m.Y()))) {
					links.add(new Link(n, m));
				}
			}
		}
	}
	
	@Override
	public String toString() {
		String retStr = "";
		
		retStr += "Nodes: ";
		for (Node n : nodes) {
			retStr += n.toString() + ", ";
		}
		retStr += "\nLinks: ";
		for (Link l : links) {
			retStr += l.toString() + ", ";
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
		
	}

}
