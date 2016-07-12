import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.joda.time.DateTime;
import org.joda.time.Interval;


public class HotelRoom {
	
	enum RoomType {
		REGULAR(100.00, "Regular"),
		PENTHOUSE(150.00, "Penthouse");
		
		private final double price;
		
		private final String name;
		
		public String roomType() {
			return name;
		}
		
		public double price() {
			return price;
		}
		
		private RoomType(double price, String name) {
			this.price = price;
			this.name = name;
		}
		
	}

	private DateTime startDate;
	
	private DateTime endDate;
	
	private Interval duration;
	
	private BigDecimal price;
	
	private RoomType type;
	
	public HotelRoom(DateTime startDate, DateTime endDate, RoomType type) {
		
		this.startDate = startDate;
		
		this.endDate = endDate;
		
		duration = new Interval(this.startDate.getMillis(), this.endDate.getMillis());
		
		price = new BigDecimal(type.price()).round(new MathContext(2, RoundingMode.HALF_EVEN));
		
		this.type = type;
		
	}
	
	public RoomType getType() {
		return type;
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
	
	@Override
	public boolean equals(Object o) {
		
		if (o == null) {
			return false;
		}
		if (o == this)  {
			return true;
		}
		if (!(o instanceof HotelRoom)) {
			return false;
		}
		
		HotelRoom r = (HotelRoom) o;
		
		if (r.duration.equals(this.duration) && r.endDate.equals(this.endDate)
				&& r.price.equals(this.price) && r.startDate.equals(this.startDate)
				&& r.type.equals(this.type)) {
			return true;
		}
		
		return false;
		
	}
	
}
