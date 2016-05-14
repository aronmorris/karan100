import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

import com.aronmorris.graph.Graph;
import com.aronmorris.graph.Node;


public class EulerianPath {

	public static void main(String[] args) {
		
		Set<Node> nodes = new LinkedHashSet<Node>();
		
		boolean addXY = true;
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
		
		
		
	}
	
	public static boolean isEulerian(Graph graph) {
		
		Stack<Node> nodes = new Stack<Node>();
		
		return false; //TODO change to actual return condition
	}
	
	
}
