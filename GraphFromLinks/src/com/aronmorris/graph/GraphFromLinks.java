package com.aronmorris.graph;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;


public class GraphFromLinks {

	public static void main(String[] args){
		
		Random rand = new Random();
		
		Set<Node> nodes = new LinkedHashSet<Node>();
		/*
		for (int i = 0; i < 10; i++) {
			nodes.add(new Node(rand.nextInt(20), rand.nextInt(20)));
		}
		*/
		nodes.add(new Node(1, 1));
		nodes.add(new Node(1, 2));
		nodes.add(new Node(1, 3));
		nodes.add(new Node(1, 4));
		nodes.add(new Node(2, 1));
		nodes.add(new Node(1, 1));
		
		Graph graph = new Graph(nodes);
		
		System.out.println(graph.toString());
		Node[] arr = new Node[nodes.size()];
		System.out.println(graph.getDegreeOfNode(nodes.toArray(arr)[0]));
	
	}
	
}
