
//abstract Flower class defines the specific requirements of each descendant type of flower
public abstract class Flower {

	private String name;
	
	public Flower(String name) {
		//lowercased to prevent the nonsense of having "peonies" and "Peonies" as separate things
		this.name = name.toLowerCase();
		
	}
	
	public String getName() {
		return name;
	}
	
}
