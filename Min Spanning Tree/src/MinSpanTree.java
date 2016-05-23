import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

import com.aronmorris.graph.Graph;
import com.aronmorris.graph.Graph.Node;


public class MinSpanTree {

	public static void main(String[] args) {
		
	}
	
	//Boruvka algorithm
	public static void minSpanTree(Graph graph, Set<TLink> linkSet) {
		
		LinkedList<Node> nodes = graph.getAllNodes();
		
		LinkedList<Tree> forest = new LinkedList<Tree>();
		
		for (Node n : nodes) { // Initialize a forest T to be a set of one-vertex trees
			TNode tn = new TNode(n);
			Tree t = new Tree(tn);
			forest.add(t);
		}
		
		while (forest.size() > 1) {
			for (Tree t : forest) {
				ArrayList<TLink> links = new ArrayList<TLink>();
				for (TNode tn : t.getNodesInTree()) {
					int shortest = Integer.MAX_VALUE;
					TLink shortestLink = null;
					for (TLink l : linkSet) {
						if (l.getWeight() < shortest && (l.A().equals(tn) || l.B().equals(tn))) {
							shortest = l.getWeight();
							shortestLink = l;
						}
					}
				}
				
			}
		}
		
	}
	
	
}
