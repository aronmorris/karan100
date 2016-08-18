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
	
	public void addPerson(Person p) {
		relationships.put(p, new ArrayList<Relationship>()); //no relations at first
	}
	
	public void addRelation(Person A, Person B, RType relation) {
		//I'll admit this code is suboptimal at the moment
		for (Iterator<Relationship> it = relationships.get(A).iterator(); it.hasNext();) {
			Relationship temp = it.next();
			if (temp.getRelatedA().equals(A) || temp.getRelatedB().equals(A)) {
				//do nothing, these people are related
			}
		}
		for (Iterator<Relationship> it = relationships.get(B).iterator(); it.hasNext();) {
			Relationship temp = it.next();
			if (temp.getRelatedA().equals(B) || temp.getRelatedB().equals(B)) {
				//do nothing, these people are related
			}
		}
		
		if (relation == RType.PARENT) { //a parent-child relation is not reflexive
			Relationship r = new Relationship(A, B, relation);
			relationships.get(A).add(r);
			
			r = new Relationship(B, A, RType.CHILD);
			relationships.get(B).add(r);
		}
		else if (relation == RType.CHILD) { //case of child being the defined relation
			Relationship r = new Relationship(A, B, relation);
			relationships.get(A).add(r);
			
			r = new Relationship(B, A, RType.PARENT);
			relationships.get(B).add(r);
		}
		else { //reflexive for siblings & partners
			Relationship r = new Relationship(A, B, relation);
			relationships.get(A).add(r);
			
			r = new Relationship(B, A, relation);
			relationships.get(B).add(r);
		}
		
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
