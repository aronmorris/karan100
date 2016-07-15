import java.util.HashMap;
import java.util.LinkedList;

import com.aronmorris.graph.Graph;
import com.aronmorris.graph.Graph.Node;


public class Dijsktra {

	public static void main(String[] args) {
		
Graph graph = new Graph();
				
		boolean addXY = true;
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
		
		dijsktra(graph, graph.get(0));
		
	}
	
	public static void dijsktra(Graph graph, Node source) {
		
		LinkedList<Node> nodes = new LinkedList<Node>();
		
		HashMap<Node, Integer> dist = new HashMap<Node, Integer>();
		HashMap<Node, Integer> prev = new HashMap<Node, Integer>();
		
		for (Node n : graph.getAllNodes()) {
			dist.put(n, Integer.MAX_VALUE);
			prev.put(n, Integer.MAX_VALUE);
			nodes.add(n);
		}
		
		dist.put(source, 0); //starting node is 0 distance away from itself
	
		while(!nodes.isEmpty()) {
			
			int shortest = Integer.MAX_VALUE, temp;
			Node key = null;
			for (Node n : nodes) {
				temp = dist.get(n);
				if (temp < shortest) {
					temp = shortest;
					key = n;
				}
			}
			
			Node u = key;
			
			nodes.remove(u);
			
			System.out.printf("Removed %s%n", u);
			
			for (Node n : graph.getRelatedNodes(u)) {
				int alt = dist.get(u) + dist(u, n);
				System.out.printf("Alt = %d%n", alt);
				if (alt < dist.get(n)) {
					System.out.printf("Alt shorter than %s%n", dist.get(n));
					dist.put(n, alt);
					prev.put(n, alt);
				}
			}
			
		}
		
		System.out.printf("Distance of nodes from source:%s%n"
				+ "distance: %s%n"
				+ "previous distance: %s", source, dist, prev);
		
	}
	
	public static int dist(Node n1, Node n2) {
		return (int) Math.sqrt(Math.pow(n2.X() - n1.X(), 2) + Math.pow(n2.Y() - n1.Y(), 2)); 
	}
	
}
