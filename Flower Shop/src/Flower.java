

public class Flower {

	private String name;
	
	public Flower(String name) {
		//lowercased to prevent the nonsense of having "peonies" and "Peonies" as separate things
		this.name = name.toLowerCase();
		
	}
	
	public String getName() {
		return name;
	}
	
}
