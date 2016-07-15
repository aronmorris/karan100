import java.util.LinkedList;
import java.util.Set;

import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.aronmorris.graph.Graph;
import com.aronmorris.graph.Graph.Node;


public class MinSpanTree {

	public static void main(String[] args) {
		
		SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		for (int i = 0; i < 10; i++) {
			graph.addVertex(i);
			if (i > 0) {
				graph.addEdge(i, i - 1);
			}
		}
		
		KruskalMinimumSpanningTree<Integer, DefaultWeightedEdge> kmst = kruskal(graph);
		
		System.out.println("Edge set: " + kmst.getMinimumSpanningTreeEdgeSet().toString());
		System.out.println("Total weight: " + kmst.getMinimumSpanningTreeTotalWeight());
		
	}
	
	//starting over with graph from external lib
	public static KruskalMinimumSpanningTree<Integer, DefaultWeightedEdge> kruskal(SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph) {
		KruskalMinimumSpanningTree<Integer, DefaultWeightedEdge> kmst = new KruskalMinimumSpanningTree<Integer, DefaultWeightedEdge>(graph);
		return kmst;
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
				//ArrayList<TLink> links = new ArrayList<TLink>();
				for (TNode tn : t.getNodesInTree()) {
					int shortest = Integer.MAX_VALUE;
					@SuppressWarnings("unused")
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
