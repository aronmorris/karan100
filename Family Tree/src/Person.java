import java.util.Date;


public class Person {

	private String name;
	private Date birth;
	
	public Person(String name, Date birthdate) {
		this.name = name;
		birth = birthdate;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getBirthDate() {
		return birth;
	}
	
}
