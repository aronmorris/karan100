import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

import com.aronmorris.graph.Graph;
import com.aronmorris.graph.Graph.Link;
import com.aronmorris.graph.Graph.Node;

public class EulerianPath {

	public static void main(String[] args) {
		
		boolean addXY = true;
		
		Graph graph = new Graph();
		
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
		
		
		System.out.println(graph.toString());
		
		try {
			System.out.println(graph.getDegreeOfNode(graph.get(0)));
			System.out.println((isEulerian(graph)));
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("No nodes.");
		}
		
	}
	
	public static boolean isEulerian(Graph graph) {
		
		///LinkedList<Link> links = graph.getLinks();
		
		//LinkedHashSet<Node> nodes = graph.getAllNodes();
		
		int[] degrees = new int[graph.getAllNodes().size()];
		int parity = 0;
		
		Stack<Node> ns = new Stack<Node>();
		
		LinkedList<Node> circuit = new LinkedList<Node>();
		
		Node currentNode = null;
		
		for (int i = 0; i < degrees.length; i++) {
			degrees[i] = graph.getDegreeOfNode(graph.get(i));
			parity += degrees[i] % 2; //0 if even 1 if odd
			System.out.printf("Parity at %d for node %s%n", parity, graph.get(i));
		}
		
		if (parity == 2) { //two nodes have odd degree, these must be the start and end nodes
			for (int i = 0; i < degrees.length; i++) {
				if (degrees[i] % 2 == 1) {
					currentNode = graph.get(i);
					break;
				}
			}
		}
		
		else if (parity == 0) { //all nodes are even-degree and thus all are linked to each other
			currentNode = graph.get(0);
		}
		
		else { //Parity isnt 0 or 2, so no path exists
			System.out.printf("No path exists according to parity value !0 || 2: %d%n", parity);
			return false;
		}
		
		if (currentNode.equals(null)) { //this shouldn't happen
			System.out.println("Something went wrong declaring the initial node.");
			return false;
		}
		
		do {
			
			LinkedList<Link> relatedLinks = graph.getLinksOfNode(currentNode); //getLinksOfNode returns an empty list if there's no links
			
			if (relatedLinks.isEmpty()) { //no more path to take for this node
				circuit.add(currentNode);
				if (!ns.isEmpty()) { 
					currentNode = ns.pop(); //take a step back, try again on the previously stepped node
				}
				else { //no more nodes in the stack means that the list is empty
					break;
				}
			}
			
			else {
				if (relatedLinks.get(0).A().equals(currentNode)) { //first related link 
					ns.push(currentNode);
					currentNode = relatedLinks.get(0).B();
				}
				else if (relatedLinks.get(0).B().equals(currentNode)) {
					ns.push(currentNode);
					currentNode = relatedLinks.get(0).A();
				}
				graph.removeLink(relatedLinks.get(0));
			}
			
		} while(true); //TODO set an actual condition for this
		
		Collections.reverse(circuit); //circuit is created in reverse order of how it actually appears
		
		if (circuit.isEmpty()) {
			System.out.printf("No path appears to exist.%n");
			return false;
		}
		
		for (Node n : circuit) { 
			for (Node m : circuit) {
				if (n.equals(m) && circuit.indexOf(n) != circuit.indexOf(m)) { //same item appears twice and isn't in the same spot twice
					System.out.println("Cycle exists");
					return true;
				}
			}
		}
		
		if (circuit.size() > 1) { //
			System.out.printf("Path exists.%n%s%n", circuit.toString());
			return true;
		}
		
		System.out.printf("Path/cycle conditions not satisfied.%n");
		return false; //TODO change to actual return condition
	}
	
	
}
