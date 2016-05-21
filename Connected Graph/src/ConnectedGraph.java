import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

import com.aronmorris.graph.Graph;
import com.aronmorris.graph.Graph.Link;
import com.aronmorris.graph.Graph.Node;

//Depth-first traversal
public class ConnectedGraph {

	public static void main(String[] args) {
	
		Graph graph = new Graph();
		
		graph.addNode(10, 10);
		
		boolean addXY = false;
		int x = 0, y = 1;
		for (int i = 1; i <= 10; i++) {
			//(1, 1) (2, 1), (2, 2), (3, 2), (3, 3), (4, 3)
			if (addXY == true) {
				x++;
			}
			else {
				y++;
			}
			graph.addNode(x, y);
			addXY = !addXY;
			System.out.printf("(%d, %d)%n", x, y);
		}
		
		System.out.println(graph);
		
		System.out.println(isConnected(graph));
		
	}
	
	public static boolean isConnected(Graph graph) {
		
		Map<Node, Boolean> visited = new LinkedHashMap<Node, Boolean>();
		
		LinkedList<Node> nodes = graph.getAllNodes();
		LinkedList<Link> relatedLinks = graph.getLinksOfNode(nodes.getFirst());
		
		for (Node n : nodes) {//mark all nodes as unvisited
			visited.put(n, false);
		}
		
		Stack<Node> nStack = new Stack<Node>(); 
		
		visited.put(nodes.getFirst(), true); //first node in the graph associated
		
		Node cNode = nodes.getFirst();
		
		nStack.push(cNode);
		
		int ctr = 0;
		
		do {
			
			relatedLinks = graph.getLinksOfNode(cNode);
			
			for (Iterator<Link> it = relatedLinks.iterator(); it.hasNext();) {
				Link l = it.next();
				if (visited.get(l.A()) && visited.get(l.B())) {
					if (nStack.isEmpty()) {
						return visited.containsKey(false);
					}
				}
				if (!it.hasNext()) {
					cNode = nStack.pop();
				}
			}
			
			for (Link l : relatedLinks) {
				if (l.A().equals(cNode) && !visited.get(l.B())) {
					visited.put(l.B(), true);
					cNode = l.B();
					nStack.push(l.B());
					
				}
				else if (l.B().equals(cNode) && !visited.get(l.A())) {
					visited.put(l.A(), true);
					cNode = l.A();
					nStack.push(l.A());
				}
			}
			
			System.out.printf("Stack at value: %s%n", nStack.peek());
			
	
			if (nStack.peek().equals(cNode) && ctr > 5) {
				return false;
			}
			
			ctr++;
				
			
		} while (!nStack.isEmpty());
		
		return !visited.containsKey(false);
	}
	
}
