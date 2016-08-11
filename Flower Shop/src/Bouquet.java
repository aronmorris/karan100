import java.util.HashMap;

//The bouquet class handles the not-very-concise operations of manipulating the nested map object
//Update: Changed it to a regular hashmap since the Flower object already contains its name
public class Bouquet {
	//REDUNDANT
	//Each flower type's name is the key to that flower and the number of that flower in this bouquet
	private HashMap<Flower, Integer> bouquet;
	
	//bouquet doesn't take any arguments - all bouquets start empty
	public Bouquet() {
		bouquet = new HashMap<Flower, Integer>();
	}
	
	//adds flowers to the bouquet, taking a tuple of the Flower and how many are desired as args
	//if a bouquet already contains a specific flower its quantity is incremented instead of being replaced
	public void addToBouquet(FlowerTuple... ft) {
		for (FlowerTuple f : ft) {
			if (bouquet.containsKey(f.FLOWER)) {
				int quantity = f.COUNT + bouquet.get(f.FLOWER);
				bouquet.put(f.FLOWER, quantity);
			}
			else {
				bouquet.put(f.FLOWER, f.COUNT);
			}
		}
	}
	
	public void discardFromBouquet(Flower f) {
		if (bouquet.containsKey(f)) {
			bouquet.remove(f);
		}
	}
	
	
		
}
