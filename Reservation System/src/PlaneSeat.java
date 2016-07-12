import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class PlaneSeat {

	enum SeatClass {
		ECONOMY(50, "Economy"),
		BUSINESS(100, "Business"),
		FIRST_CLASS(200, "First Class");
		
		private final double price;
		
		private final String name;
		
		String getName() {
			return name;
		}
		
		double getPrice() {
			return price;
		}
		
		private SeatClass(double price, String name) {
			this.price = price;
			this.name = name;
		}
	}
	
	private BigDecimal price;
	
	public PlaneSeat(SeatClass sClass) {
		price = new BigDecimal(sClass.getPrice()).round(new MathContext(4, RoundingMode.HALF_EVEN));
	}
	
	public double getPrice() {
		return price.doubleValue();
	}
	
}
