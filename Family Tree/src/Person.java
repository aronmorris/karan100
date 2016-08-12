import java.util.Date;

//You can only assign a child to a person by having someone assign that person as their parent.
//Partners can be assigned and reassigned without much trouble
public class Person {

	private String name;
	private Date birth;
	private Date death;
	
	private boolean hasDeath;
	
	private Person partner;
	
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
	
	public void setPartner(Person p) {
		partner = p;
	}
	
	public Person getPartner() {
		return partner;
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n" + name + "\n");
		sb.append("Born: " + birth.toString() + "\n");
		
		if (hasDeath) sb.append("Died: " + death.toString() + "\n");
		
		if (parents != null) {
			for (Person p : parents) {
				sb.append("Parent: " + p.getName());
			}
		}
		
		return sb.toString();
		
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (!(obj instanceof Person)) {
	        return false;
	    }
	  
	    if (obj == this) {
	    	return true;
	    }
	
	    Person p = (Person) obj;
	    
	    if (this.name.equals(p.name) && this.birth.equals(this.birth)) {
	    	return true;
	    }
	    
	    return false;
	    
	}
	
}
