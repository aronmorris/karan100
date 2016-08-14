import java.util.ArrayList;
import java.util.HashMap;

public class FamilyTree {

	//each person has a list of their relations with other people
	//The only really suitable model for a family tree is a graph, not a tree (2 parent nodes per child node)
	//View a Person as a vertex and a Relationship as an edge
	private HashMap<Person, ArrayList<Relationship>> relationships;
	
	public FamilyTree() {
		relationships = new HashMap<Person, ArrayList<Relationship>>();
	}
	
}
