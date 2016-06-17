import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>();
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }
  
    public static class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;
        
        public T getData() {
        	return this.data;
        }
        
        public void addChild(T data) {
        	Node<T> newNode = new Node<T>();
        	newNode.parent = this;
        	this.children.add(newNode);
        }
        
        public Node<T> getParent() {
        	return this.parent;
        }
        
    }
   
}
