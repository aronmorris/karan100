import java.util.Date;

//A Person is immutable once set so their position on the tree doesn't change
//You can only assign a child to a person by having someone assign that person as their parent.
public class Person {

	private String name;
	private Date birth;
	private Date death;
	
	private boolean hasDeath;
	
	private Person[] parents;
	
	public Person(String name, Date birth) {
		this.name = name;
		this.birth = birth;
		
		hasDeath = false;
	}
	
	public Person[] getParents() {
		return parents;
	}
	
	public void setParents(Person... parents) {
		if (parents.length > 1) {
			System.out.println("You can only have two parents!");
		}
		else {
			this.parents = parents;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public void setDeath(Date d) {
		if (hasDeath) {
			System.out.println("This person has already died!");
		}
		else {
			death = d;
			hasDeath = true;
		}
	}
	
	public Date getDeath() {
		if (hasDeath) {
			return death;
		}
		else {
			System.out.println("This person hasn't died, returning null.");
			return null;
		}
	}
	
}
