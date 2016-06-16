import java.util.ArrayList;

//immutable
public class Node<E> {

	private Node<E> parent = null;
	private ArrayList<Node<E>> children;
	
	private E data;
	
	public Node(E object) {
		data = object;
		children = new ArrayList<Node<E>>();
	}
	
	public Node(E object, Node<E> parent) {
		this(object);
		this.parent = parent;
	}
	
	public E getData() {
		return data;
	}
	
	public boolean hasParent() {
		if (parent != null) {
			return true;
		}
		return false;
	}
	
	public Node<E> getParent() {
		if (hasParent()) {
			return parent;
		}
		return null;
	}
	
	public boolean hasChildren() {
		return !children.isEmpty();
	}
	
	public ArrayList<Node<E>> getChildren() {
		return new ArrayList<Node<E>>(children);
	}
	
}
