import java.util.ArrayList;
import java.util.List;

import com.aronmorris.graph.Graph.Node;

public class TNode extends Node {
	
	private List<TNode> children;
	
	private Node node;
	
	public TNode(Node n) {
		super(n.X(), n.Y());
		children = new ArrayList<TNode>();
		this.node = n;
	}
	
	public TNode(int a, int b) {
		super(a, b);
		children = new ArrayList<TNode>();
	}
	
	public TNode(int a, int b, TNode[] children) {
		super(a, b);
		for (TNode t : children) {
			this.children.add(t);
		}
	}
	
	@Override
	public boolean equals(Object o) {
		TNode n = (TNode) o;
		return super.equals(n.getWrappedNode());
	}
	
	public Node getWrappedNode() {
		return this.node;
	}
	
	public ArrayList<TNode> getAllChildNodesInList() {
		ArrayList<TNode> nodes = new ArrayList<TNode>();
		if (!this.hasChildren()) {
			nodes.add(this);
		}
		else {
			nodes.addAll(this.getAllChildNodesInList());
		}
		return nodes;
	}
	
	public void addChild(TNode t) {
		children.add(t);
	}
	
	public void addChildren(List<TNode> l) {
		children.addAll(l);
	}
	
	public List<TNode> getAllChildren() {
		return this.children;
	}
	
	public void removeChild(TNode t) {
		children.remove(t);
	}
	
	public boolean hasChildren() {
		return !children.isEmpty();
	}
	
}
