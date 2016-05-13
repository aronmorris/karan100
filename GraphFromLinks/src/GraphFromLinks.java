import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;


public class GraphFromLinks {

	public static void main(String[] args){
		
		Random rand = new Random();
		
		Set<Node> nodes = new LinkedHashSet<Node>();
		
		for (int i = 0; i < 10; i++) {
			nodes.add(new Node(rand.nextInt(20), rand.nextInt(20)));
		}
		
		Graph graph = new Graph(nodes);
		
		System.out.println(graph.toString());
		
	}
	
}
