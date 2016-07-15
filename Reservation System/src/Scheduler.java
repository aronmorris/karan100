import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;


public class Scheduler {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		Hotel h = new Hotel();
		
		Scheduler scheduler = new Scheduler();
		
		//scheduler.listPricesForRooms();
		//scheduler.listPricesForSeats();
		
		DateTime booking = new DateTime();
		
		DateTime end = new DateTime(2016, 7, 22, 15, 40);
		
		HotelRoom hr = new HotelRoom(booking, end, HotelRoom.RoomType.PENTHOUSE);
		
		PlaneSeat ps = new PlaneSeat(PlaneSeat.SeatClass.FIRST_CLASS);
		
		scheduler.bookFlight(ps);
		
		for (int i = 0; i < 6; i++) {
			scheduler.bookRoom(hr);
		}
		
	}
	
	public Scheduler() {
		//Hotel hotel = new Hotel();
	}
	
	public void bookFlight(PlaneSeat ps) {
		
		System.out.printf("A seat in the %s class costs $%.2f dollars.%n", ps.getType().getName(), ps.getPrice());
		
	}
	public void bookRoom(HotelRoom hr) {
		if (Hotel.bookRoom(hr)) {
			System.out.println("Room booked!");
		}
		else {
			System.out.println("Date unavailable for this room type.");
			System.out.println("Dates become available starting at: ");
			
			DateTime earliest = new DateTime(Long.MAX_VALUE); //datetime at end of unix time
		
			for (HotelRoom room : Hotel.getRooms()) {
				if (room != null) {
					if (room.getAvailableDate().isBefore(earliest)) {
						earliest = room.getAvailableDate();
					}
				}
			}
			
			org.joda.time.format.DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
			
			System.out.println(fmt.print(earliest));
			
		}
	}
	
	public void listPricesForRooms() {
		for (HotelRoom.RoomType type : HotelRoom.RoomType.values()) {
			System.out.println("Room type: " + type.roomType());
			System.out.println("Room price: " + type.price() + "\n");
		}
	}
	
	public void listPricesForSeats() {
		for (PlaneSeat.SeatClass seat : PlaneSeat.SeatClass.values()) {
			System.out.println("Seat class: " + seat.getName());
			System.out.println("Class price: " + seat.getPrice() + "\n");
			
		}
	}
	
	
	
}
