import java.util.HashMap;


/*The shop class deals in creating and selling bouquets from flowers in its inventory
 * as well as ordering more flowers when stock is low and keeping its inventory maintained
 */
public class Shop {
	
	public static void main(String[] args) {
		
		Shop shop = new Shop();
		
		shop.addFlowerToInventory(new Flower("Rose"), 10);
		
		shop.addFlowerToInventory(new Flower("Sunflower"), 10);
		
		shop.addFlowerToInventory(new Flower("Orchid"), 10);
		
		//bouquet of 3 roses
		shop.sell(shop.createBouquet(new FlowerTuple(lookup.get("rose"), 3)));
		
		shop.sell(shop.createBouquet(new FlowerTuple(lookup.get("rose"), 5), new FlowerTuple(lookup.get("orchid"), 8)));
		
		shop.sell(shop.createBouquet(new FlowerTuple(lookup.get("rose"), 5), new FlowerTuple(lookup.get("orchid"), 8)));

		
	}
	
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
			inventory.put(f, inventory.get(f) + i);
		}
		else {
			inventory.put(f, i);
		}
	}
	
	//iterates over every flower in the tuple array
	//If a particular flower has too few in the inventory or isn't present, it's not added and an alert is given.
	//An empty bouquet can be passed as a result if all tuples fail.
	public Bouquet createBouquet(FlowerTuple... flowers) {
		
		Bouquet bouquet = new Bouquet();
		
		for (FlowerTuple ft : flowers) {
			if (inventory.containsKey(ft.FLOWER)) {
				if (inventory.get(ft.FLOWER) - ft.COUNT >= 0) {
					bouquet.addToBouquet(ft);
					inventory.put(ft.FLOWER, inventory.get(ft.FLOWER) - ft.COUNT); //deducts this flower from the inventory
				}
				else {
					System.out.printf("Insufficient quantity of %s to add to bouquet.%n"
							+ "Need: %d more%n"
							+ "Have: %d%n"
							+ "Process continues.%n",
							ft.FLOWER.getName(), ft.COUNT - inventory.get(ft.FLOWER), inventory.get(ft.FLOWER));
				}
			}
			else {
				System.out.printf("No %s in this shop's inventory!%n"
						+ "Process continues.%n",
						ft.FLOWER.getName());
			}
		}
		
		return bouquet;
	}
	
	public void sell(Bouquet b) {
		System.out.println("Sold a bouquet of: \n" + b.toString());
	}
	
	public void addToBouquet(Bouquet b, Flower f, int count) {
		b.addToBouquet(new FlowerTuple(f, count));
	}
	
	//discards this flower entirely from a specific bouquet
	//Flowers can't be cut for a bouquet and then reused after for a new one
	public void discardFlowerFromBouquet(Bouquet b, String f) {
		b.discardFromBouquet(lookup.get(f));
	}
	
	
	
}
