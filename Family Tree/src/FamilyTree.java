import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class FamilyTree {
	
	public static void main(String[] args) {
		FamilyTree ft = new FamilyTree();
		
		ft.addPerson(new Person("Larry", new Date()));
		
		ft.getPerson("Larry").setDeath(new Date());
		
		ft.addChild(new Person("Sheila", new Date()), ft.getPerson("Larry"));
		
		System.out.println(ft.people);
		
	}
	
	/*
	 * Consider replacing this with a TreeNode from swing
	 */
	private LinkedList<Person> people;
	
	public FamilyTree() {
		people = new LinkedList<Person>();
	}
	
	public void addPerson(Person p) {
		people.add(p);
	}
		
	//iterate over every person object in the linkedlist to find the name, return null if none found
	public Person getPerson(String name) {
		
		for (Iterator<Person> it = people.iterator(); it.hasNext();) {
			Person p = it.next();
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	public Person addChild(Person child, Person... parents) {
		child.setParents(parents);
		if (!people.contains(child)) {
			people.add(child);
		}
		return child;
	}
	
	public String toString() {
		
		//HashMap<Person, ArrayList<Person>> tree 
		
		for (Person p : people) {
			if (p.getParents() != null) {
		
			}
		}
	}
	
}
