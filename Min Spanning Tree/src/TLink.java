import com.aronmorris.graph.Graph.Link;


public class TLink extends Link {

	private TNode A, B;
	
	private final int weight;
	
	protected TLink(TNode a, TNode b, int linkWeight) {
		super(a.getWrappedNode(), b.getWrappedNode());
		
		this.weight = linkWeight;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public TNode A() {
		return A;
	}
	
	public TNode B() {
		return B;
	}

}
