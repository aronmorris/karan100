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
		
	
		Link[] links = new Link[graph.getLinks().size()];
		Node[] nodes = new Node[graph.getNodes().size()];
		graph.getNodes().toArray(nodes);
		graph.getNodes().toArray(links);
	
		Stack<Node> nodeStack = new Stack<Node>();
		LinkedList<Node> circuit = new LinkedList<Node>();
		
		
		int[] degreeArr = new int[nodes.length];
		int oddCtr = 0;
		int startIndex;
	
		
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
		}
		
		
		
		
		return false; //TODO change to actual return condition
	}
	
	
}
