import java.util.ArrayList;
import java.util.List;

public class Node<T> {
	  
    private T data;
    private Node<T> parent;
    private List<Node<T>> children;

   public Node(T data1) {
	   this.data = data1;
	   this.children = new ArrayList<Node<T>>();
	   parent = this;
   }
   
   public Node(T data, Node<T> parent) {
	   this.parent = parent;
	   this.parent.children.add(this);
   }
   
   public boolean hasChildren() {
	   if (!this.children.isEmpty()) {
		   return true;
	   }
	   return false;
   }
        
   public ArrayList<Node<T>> getChildren() {
	   return (ArrayList<Node<T>>) this.children;
   }
   
    public T getData() {
    	return this.data;
    }
    
    public void addChild(T data) {
    	//adds a new node as a child node of the parent
    	new Node<T>(data, this);
    }
    
    public Node<T> getParent() {
    	return this.parent;
    }
        
    @Override
    public String toString() {
    	return data.toString();
    }
   
}
