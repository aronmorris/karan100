
public class Node {

	private Node next;
	
	private Node prev;
	
	private Object data;
	
	public Node() {
		
		this.prev = null;
		
		this.next = null;
		
		this.data = null;
		
	}
	
	public Node(Node previous, Node next, Object storeData) {
		
		this.prev = previous;
		
		this.next = next;
		
		this.data = storeData;
		
	}
	
	public Object element() {
		
		return this.data;
		
	}
	
	public void setData(Object newData) {
		
        this.data = newData;
        
    }

    public void setNextNode(Node nextNode) {
    	
        this.next = nextNode;
        
    }
    
    public Node getNextNode() {
    	
        return next;
        
    }
    
    public void setPreviousNode(Node previous) {
    	
        this.prev = previous;
        
    }

    public Node getPreviousNode() {
    	
        return prev;
        
    }
	
}
