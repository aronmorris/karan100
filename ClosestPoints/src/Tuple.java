
public class Tuple implements Comparable<Tuple> {

	private int x, y;
	
	public Tuple(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int X() {
		return this.x;
	}
	
	public int Y() {
		return this.y;
	}

	@Override
	public String toString() {
		return " " + this.X() + ", " + this.Y();
	}

	@Override
	public int compareTo(Tuple o) {
		
		return this.X() > o.X() ? this.X() : o.X();
	}
	
	
}
