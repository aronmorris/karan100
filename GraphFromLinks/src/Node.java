
/**
 * Node is a class which defines a node in 2 dimensions with an X and Y coordinate
 * Used as a singular component of a graph
 * @author Aron
 *
 */
public final class Node {

	private int X, Y;
	
	public Node() {
		this.X = 0;
		this.Y = 0;
	}
	
	public Node(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	public int X() {
		return this.X;
	}
	
	public int Y() {
		return this.Y;
	}
	
	@Override
	public String toString() {
		return "(" + this.X + ", " + this.Y + ")";
	}
	
}
