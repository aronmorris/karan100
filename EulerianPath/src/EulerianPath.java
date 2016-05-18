import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import com.aronmorris.graph.Graph;

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
			//System.out.println(new Node(x, y));
		}
		
		//System.out.println(graph.toString());
		
		System.out.println((graph.toString()));
		
	}
	
	public static boolean isEulerian(Graph graph) {
		
	
		
		return false; //TODO change to actual return condition
	}
	
	
}
