import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class PlaneSeat {

	enum SeatClass {
		ECONOMY(50),
		BUSINESS(100),
		FIRST_CLASS(200);
		
		private final double price;
		
		double getPrice() {
			return price;
		}
		
		private SeatClass(double price) {
			this.price = price;
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
