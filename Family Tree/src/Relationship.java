
public abstract class Relationship {

	private Person A;
	private Person B;
	
	public Relationship(Person A, Person B) {
		this.A = A;
		this.B = B;
	}
	
	public Person getRelatedA() {
		return A;
	}
	
	public Person getRelatedB() {
		return B;
	}
	
}
