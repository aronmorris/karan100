import java.util.ArrayList;
import java.util.List;

import com.aronmorris.graph.Graph.Node;


public class Tree {

	private TNode root;
	
	public Tree(TNode rootNode) {
		this.root = new TNode(rootNode.X(), rootNode.Y());
		if (rootNode.hasChildren()) {
			this.root.addChildren(rootNode.getAllChildren());
		}
	}
	
}

class TNode extends Node {
	
	private List<TNode> children;
	
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
