import org.joda.time.DateTime;


public class Scheduler {
	
	private HotelRoom reservation;
	
	private PlaneSeat seat;

	public Scheduler(PlaneSeat seat, HotelRoom room) {
		reservation = room;
		this.seat = seat;
	}
	
	public boolean isRoomAvailableAtDate(DateTime sDate) {
		return reservation.isReservedAtDate(sDate);
	}
	
}
