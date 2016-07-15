package com.aronmorris.graph;


public class GraphFromLinks {

	public static void main(String[] args){
			
		/*
		for (int i = 0; i < 10; i++) {
			nodes.add(new Node(rand.nextInt(20), rand.nextInt(20)));
		}
		*/
		
		
		Graph graph = new Graph();
		
		for (int i = 0; i < 10; i++) {
			graph.addNode(i, i+1);
		}
		
		System.out.println(graph.toString());
		
	}
	
}
