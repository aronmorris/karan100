import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class FamilyTree {
	
	public static void main(String[] args) {
		FamilyTree ft = new FamilyTree();
		
		ft.addPerson(new Person("Larry", new Date()));
		
		//ft.getPerson("Larry").setDeath(new Date());
		
		//Person sheila = new Person("Sheila", new Date());
		
		//sheila.setParents(ft.getPerson("Larry"));
		
		ft.addChild(new Person("Sheila", new Date()), ft.getPerson("Larry"));
		
		System.out.println(people.toString());
		
		System.out.println("");
		
		System.out.println(ft.toString());
		
	}
	
	/*
	 * Consider replacing this with a TreeNode from swing
	 */
	private static LinkedList<Person> people;
	
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
		StringBuilder sb = new StringBuilder();
		
		for (Person p : people) {
			sb.append(p.getName()); //assign the person
			if (p.getPartner() != null) { 				//if they have a partner		"A --- B"
				sb.append(" --- " + p.getPartner() + "\n");	//assign the partner like so:	"	|	"	
				sb.append(getBlankStringOfLength(p.getName().length()) + "  |  " + getBlankStringOfLength(p.getPartner().getName().length()) + "\n");
			}
			else {
				sb.append("/n" + sb.append(getBlankStringOfLength(p.getName().length() / 2)) + "|\n");
			}
			
		}
		
		return sb.toString();
	}
	
	
	private String getBlankStringOfLength(int length) {
		if (length > 0) {
			char[] arr = new char[length];
			Arrays.fill(arr, ' ');
			return new String(arr);
		}
		return "";
	}
}
