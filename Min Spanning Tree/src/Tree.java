import java.util.ArrayList;


public class Tree {

	private TNode root;
	
	public Tree(TNode rootNode) {
		this.root = new TNode(rootNode.X(), rootNode.Y());
		if (rootNode.hasChildren()) {
			this.root.addChildren(rootNode.getAllChildren());
		}
	}
	
	public ArrayList<TNode> getNodesInTree() {
		return root.getAllChildNodesInList();
	}
	
}
