import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;


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

	private Date reserved;
	
	private Date available;
	
	private BigDecimal price;
	
	public HotelRoom(Date startDate, Date endDate, RoomType type) {
		
		reserved = startDate;
		
		available = endDate;
		
		price = new BigDecimal(type.price()).round(new MathContext(2, RoundingMode.HALF_EVEN));
		
	}
	
	public boolean isReservedAtDate(Date sDate, Date eDate) {
		//if starts after it's taken or starts before it's available
		return (!sDate.after(reserved) && !sDate.before(available));
		
	}
	
	public Date getAvailableDate() {
		return available;
	}
	
	public double getPrice() {
		return price.doubleValue();
	}
	
}
