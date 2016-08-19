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
	
	
	public void addRelation(String A, String B, RType relation) {
		boolean trueA = false, trueB = false;
		Person pA = null;
		Person pB = null;
		/* Commenting this because it's a weird looking bit of code
		 * Iterates over every person in the relationship keyset, checking to see if the two strings match
		 * the names of two people in the list. If one is a match, the first boolean switches to true and the first person is assigned.
		 * The second conditional will never be checked once the first person is assigned, preventing constant reassignement
		 * to the latest person in the loop.
		 * 
		 * The second conditional does the same but only triggers once another name match is found, and then breaks the loop.
		 */
		for (Person p : relationships.keySet()) {
			if (p.getName().equalsIgnoreCase(A) || p.getName().equalsIgnoreCase(B)) {
				
				if (trueA) {
					trueB = true;
					pB = p;
					break;
				}
				if (!trueB && !trueA) {
					trueA = true;
					pA = p;
				}
			}
		}
		
		if (trueA && trueB) { //only do this if both people are on the list (trueB would be false otherwise
			addRelation(pA, pB, relation);
		}
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
