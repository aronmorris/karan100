import java.util.HashMap;

//The bouquet class handles the not-very-concise operations of manipulating the nested map object
public class Bouquet {
	//Each flower type's name is the key to that flower and the number of that flower in this bouquet
	private HashMap<String, HashMap<Flower, Integer>> bouquet;
	
	public Bouquet() {
		bouquet = new HashMap<String, HashMap<Flower, Integer>>();
	}
	
}
