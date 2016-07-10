import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.joda.time.DateTime;
import org.joda.time.Interval;


public class HotelRoom {
	
	enum RoomType {
		REGULAR(100.00),
		PENTHOUSE(150.00);
		
		private final double price;
		
		double price() {
			return price;
		}
		
		private RoomType(double price) {
			this.price = price;
		}
		
	}

	private DateTime startDate;
	
	private DateTime endDate;
	
	private Interval duration;
	
	private BigDecimal price;
	
	public HotelRoom(DateTime startDate, DateTime endDate, RoomType type) {
		
		this.startDate = startDate;
		
		this.endDate = endDate;
		
		duration = new Interval(this.startDate.getMillis(), this.endDate.getMillis());
		
		price = new BigDecimal(type.price()).round(new MathContext(2, RoundingMode.HALF_EVEN));
		
	}
	
	public boolean isReservedAtDate(DateTime sDate) {
		
		return duration.contains(sDate.getMillis());
		
	}
	
	public DateTime getAvailableDate() {
		return endDate;
	}
	
	public Interval getReservedDuration() {
		return duration;
	}
	
	public double getPrice() {
		return price.doubleValue();
	}
	
}
