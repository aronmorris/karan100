import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import com.aronmorris.graph.Graph;
import com.aronmorris.graph.Graph.Link;
import com.aronmorris.graph.Node;


public class EulerianPath {

	public static void main(String[] args) {
		
		Set<Node> nodes = new LinkedHashSet<Node>();
		
		boolean addXY = true;
		
		nodes.add(new Node(1, 1));
		
		int x = 0, y = 1;
		for (int i = 1; i <= 10; i++) {
			//(1, 1) (2, 1), (2, 2), (3, 2), (3, 3), (4, 3)
			if (addXY == true) {
				x++;
			}
			else {
				y++;
			}
			nodes.add(new Node(x, y));
			addXY = !addXY;
			System.out.println(new Node(x, y));
		}
		
		Graph graph = new Graph(nodes);
		
		//System.out.println(graph.toString());
		
		System.out.println(isEulerian(graph));
		
	}
	
	public static boolean isEulerian(Graph graph) {
		
	
		Graph tGraph = graph;
		
		//System.out.println(tGraph.toString());
		
		LinkedHashSet<Link> links = tGraph.getLinks();
		Node[] nodes = new Node[tGraph.getNodes().size()];
		graph.getNodes().toArray(nodes);	
		Stack<Node> nodeStack = new Stack<Node>();
		LinkedList<Node> circuit = new LinkedList<Node>();
		
		
		int[] degreeArr = new int[nodes.length];
		int oddCtr = 0;
		int startIndex = 0;
	
		

		for (int i = 0; i < degreeArr.length; i++) {
			
			degreeArr[i] = tGraph.getDegreeOfNode(nodes[i]);
			System.out.println("Node: " + nodes[i] + " has degree: " + degreeArr[i]);
		}
		
		System.out.println(tGraph.toString());
		
		for (int i = 0; i < degreeArr.length; i++) {
			
			degreeArr[i] = tGraph.getDegreeOfNode(nodes[i]);
			System.out.println(degreeArr[i]);
			
			/*If there are two and only two odd degree nodes, then either of them can be the starting node on the Euler path
			 * This selects the second as the path or returns false when there aren't precisely two nodes of odd degree
			 */
			if (degreeArr[i] % 2 != 0) {
				oddCtr++;
				startIndex = i; 
				System.out.println("oddCtr iterated to " + oddCtr);
				if (oddCtr > 2 || (i == degreeArr.length - 1 && oddCtr < 2)) { //No path exists unless there are two and only two odd degree nodes
					System.out.println("No path exists");
					return false; 
				}
			}
			
			else if (oddCtr == 0 && i == degreeArr.length - 1) { //last iteration and no odd degree nodes at all
				startIndex = 0; //path can begin anywhere
				System.out.println("No odd degrees at all");
			}
			else {
				System.out.println("Neither condition met");
				return false; //if neither of the conditions is met then the graph cannot be Euler-compliant
			}
		}
		
		
		//TODO continue algorithm
		while(tGraph.hasLinks()) {
			for (Iterator<Link> it = links.iterator(); it.hasNext();) {
				
				Node currentNode = nodes[startIndex];
				Link l = it.next();
				
				if (tGraph.getDegreeOfNode(currentNode) == 0) { //current node has no links
					circuit.add(currentNode);
					currentNode = nodeStack.pop();
					System.out.println("No neighbors");
				}
				
				else if (l.A().equals(currentNode) && l.B().equals(nodes[startIndex])) { //reflective case
					it.remove();
					System.out.println("Reflective");
				}
				
				
				else if (l.A().equals(currentNode)) { //if a link contains this node, then its other node must be linked to it
					nodeStack.add(currentNode); //add the current node to the index
					
					currentNode = l.B(); //the linked node becomes the current node for the next search
					
					it.remove(); //sever the link between (x, y)(y, z) [though (y, z)(z, w) may still exist]
					System.out.println("Node has neighbors");
				}
				
				/* Node B is the node that is related to the one being searched - possibly not prudent to include it
				else if (l.B().equals(nodes[startIndex])) { //get the first neighbor of this node
					hasNeighbors = true;
					
					break;
				}
				*/
				
			}
			links = tGraph.getLinks();
		}
		
		System.out.println(circuit.toString());
		
		return false; //TODO change to actual return condition
	}
	
	
}
