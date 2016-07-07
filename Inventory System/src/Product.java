import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Product {

	private BigDecimal price;
	
	private final String id;
	
	private int quantity;
	
	public Product(double price, String id, int quantity) {
	
		this.price = new BigDecimal(price).round(new MathContext(4, RoundingMode.UNNECESSARY));
		
		this.id = id;
		
		this.quantity = quantity;
			
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = new BigDecimal(price).round(new MathContext(4, RoundingMode.UNNECESSARY));
	}
	
	public String getPriceString() {
		return price.toString();
	}
	
	public String getID() {
		return id;
	}
	
	public void addQuantity(int q) {
		quantity += q;
	}
	
	public void subtractQuantity(int q) {
		quantity -= q;
	}
	
	public void setQuantity(int q) {
		quantity = q;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	
	
}
