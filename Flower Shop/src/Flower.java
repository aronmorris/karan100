
//abstract Flower class defines the specific requirements of each descendant type of flower
public abstract class Flower {

	private String name;
	
	public Flower(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
