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
		
		int x = 1, y = 1;
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
		}
		
		Graph graph = new Graph(nodes);
		
		System.out.println(graph.toString());
		
		System.out.println(isEulerian(graph));
		
	}
	
	public static boolean isEulerian(Graph graph) {
		
	
		LinkedHashSet<Link> links = graph.getLinks();
		Node[] nodes = new Node[graph.getNodes().size()];
		graph.getNodes().toArray(nodes);	
		Stack<Node> nodeStack = new Stack<Node>();
		LinkedList<Node> circuit = new LinkedList<Node>();
		
		
		int[] degreeArr = new int[nodes.length];
		int oddCtr = 0;
		int startIndex = 0;
	
		
		for (int i = 0; i < degreeArr.length; i++) {
			
			degreeArr[i] = graph.getDegreeOfNode(nodes[i]);
			
			/*If there are two and only two odd degree nodes, then either of them can be the starting node on the Euler path
			 * This selects the second as the path or returns false when there aren't precisely two nodes of odd degree
			 */
			if (degreeArr[i] % 2 != 0) {
				oddCtr++;
				startIndex = i; 
				if (oddCtr > 2 || (i == degreeArr.length - 1 && oddCtr < 2)) { //No path exists unless there are two and only two odd degree nodes
					return false; 
				}
			}
			
			if (oddCtr == 0 && i == degreeArr.length - 1) { //last iteration and no odd degree nodes at all
				startIndex = 0; //path can begin anywhere
			}
			else {
				return false; //if neither of the conditions is met then the graph cannot be Euler-compliant
			}
		}
		
		
		//TODO continue algorithm
		while (true) {
			
			boolean hasNeighbors = false;
			for (Iterator<Link> it = links.iterator(); it.hasNext();) {
				
				Link l = it.next();
				
				if (l.A().equals(nodes[startIndex]) && l.B().equals(nodes[startIndex])) { //reflective case
					it.remove();
				}
				
				
				if (l.A().equals(nodes[startIndex])) { //get the first neighbor of this node
					hasNeighbors = true; //link node A is the node earlier chosen as the starting node
					nodeStack.add(nodes[startIndex]); //add the current node to the index
					it.remove(); //sever the link between (x, y)(y, z) [though (y, z)(z, w) may still exist]
					break;
				}
				/* Node B is the node that is related to the one being searched - possibly not prudent to include it
				else if (l.B().equals(nodes[startIndex])) { //get the first neighbor of this node
					hasNeighbors = true;
					
					break;
				}
				*/
				
			}
			if (!hasNeighbors) {
				
			}
			else if (hasNeighbors) {
				
			}
			
			
			break;
		}
		
		
		return false; //TODO change to actual return condition
	}
	
	
}
