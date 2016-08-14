
public class Relationship {
	
	private Person A;
	private Person B;
	
	private RType type;
	
	public Relationship(Person A, Person B, RType type) {
		this.A = A;
		this.B = B;
		
		this.type = type;
	}
	
	public Person getRelatedA() {
		return A;
	}
	
	public Person getRelatedB() {
		return B;
	}
	
	public RType getRelation() {
		return type;
	}
	
}
