
public class NodeList extends Node {

	private Node firstNode, lastNode;
	
	private int nodeArraySize;
	
	private Node[] listPositions;
	
	public NodeList() {
		
		firstNode = null;
		
		lastNode = null;
		
		nodeArraySize = 0;
		
	}
	
	public Node first() {
		
		return firstNode;
		
	}
	
	public Node last() {
		
		return lastNode;
		
	}
	
	public Node prev(int p) {
		
		try {
		
			return listPositions[p-1];
		
		} catch (ArrayIndexOutOfBoundsException e) {
			
			System.err.println("No positions precede position at index: " + p);
			
			return firstNode;
			
		}
			
	}
	
	public Node next(int p) {
		
		try {
			
			return listPositions[p + 1];
		
		} catch (ArrayIndexOutOfBoundsException e) {
			
			System.err.println("No position following position at index: " + p);
			
			return lastNode;
			
		}
		
	}
	
	public Node set(int p, Object e) {
		
		Node returnNode = listPositions[p];
		
		listPositions[p].setData(e);
		
		return returnNode;
		
	}
	
	public Node addFirst(Object e) {
		
		Node[] newPositions = new Node[listPositions.length + 1];
		
		newPositions[0].setData(e);
		
		for (int i = 1; i < newPositions.length; i++) {
			
			newPositions[i] = listPositions[i - 1];
			
		}
		
		firstNode = newPositions[0];
		
		listPositions = newPositions;
		
		return firstNode;
		
	}
	
	public Node addLast(Object e) {
		
		Node node;
		
		
		
	}
	
}
