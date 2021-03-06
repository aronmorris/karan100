
import java.util.HashMap;
import java.util.Map;

/**
 * Inventory stores products by ID in a hashmap
 * Products are accessible by their ID, and independently track
 * how many of themselves are in stock
 * 
 * @author Aron
 *
 */
public class Inventory {
	
	public static void main(String[] args) {
		
		Inventory inv = new Inventory();
	
		//populate inventory
		for (int i = 0; i < 5; i++) {
			
			inv.addProduct(new Product((Math.random() * 100) / 100, Integer.toString(i), i));
			
		}
		
		System.out.println("Product ID 4 retrieved: " + inv.getProductByID("4").toString());
		
		System.out.println("Total inv: " + inv.getTotalInventory());
		
		System.out.println("Inv of product ID 4: " + inv.getInventoryByID("4"));
		
	}
	
	private Map<String, Product> inventory;
	
	public Inventory() {
		
		inventory = new HashMap<String, Product>();
			
	}
	
	/**
	 * Add a new product to the inventory
	 * If the product is already in inventory, the quantities will be added together
	 * @param p
	 */
	public void addProduct(Product p) {
		
		if (inventory.containsKey(p.getID())) {
			inventory.get(p.getID()).addQuantity(p.getQuantity());
		}
		else {
			inventory.put(p.getID(), p);
		}
		
	}
	
	/**
	 * Entirely deletes the product from the inventory
	 * @param p
	 */
	public void deleteProductFromInventory(Product p) {
		
		inventory.remove(p.getID());
		
	}
	
	public int getTotalInventory() {
		
		int ctr = 0;
		
		for (String key : inventory.keySet()) {
			ctr += inventory.get(key).getQuantity();
		}
		
		return ctr;
		
	}
	
	public Product getProductByID(String id) {
		
		return inventory.get(id);		
		
	}
	
	public int getInventoryByID(String id) {
		
		return inventory.get(id).getQuantity();
		
	}
	
}
