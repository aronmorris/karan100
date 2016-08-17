import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FamilyTree {

	//each person has a list of their relations with other people
	//The only really suitable model for a family tree is a graph, not a tree (2 parent nodes per child node)
	//View a Person as a vertex and a Relationship as an edge
	private HashMap<Person, ArrayList<Relationship>> relationships;
	
	public FamilyTree() {
		relationships = new HashMap<Person, ArrayList<Relationship>>();
	}
	
	public Person getPerson(String name) {
		for (Person p : relationships.keySet()) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	public Relationship getRelation(Person A, Person B) {
		if (relationships.containsKey(A) && relationships.containsKey(B)) {
			for (Iterator<Relationship> it = relationships.get(A).iterator(); it.hasNext();) {
				Relationship temp = it.next();
				if (temp.getRelatedB().equals(B)) {
					return temp;
				}
			}
		}
		return null;
	}
	
	public ArrayList<Relationship> getAllRelations(Person p) {
		return relationships.get(p);
	}
	
}
