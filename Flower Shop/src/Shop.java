import java.util.HashMap;


/*The shop class deals in creating and selling bouquets from flowers in its inventory
 * as well as ordering more flowers when stock is low and keeping its inventory maintained
 */
public class Shop {
	
	//all shops ought to be able to tell what every kind of flower is regardless of if only one or every shop has it
	private static HashMap<String, Flower> lookup = new HashMap<String, Flower>();
	
	private HashMap<Flower, Integer> inventory;
	
	public Shop() {
		inventory = new HashMap<Flower, Integer>();
	}
	
	public void addFlowerToInventory(Flower f, int i) {
		if (!lookup.containsKey(f.getName())) {
			lookup.put(f.getName(), f);
		}
		if (inventory.containsKey(f.getName())) {
			inventory.put(f, i);
		}
	}
	
	//discards this flower entirely from a specific bouquet
	public void discardFlowerFromBouquet(Bouquet b, String f) {
		b.discardFromBouquet(lookup.get(f));
	}
	
	
	
}
