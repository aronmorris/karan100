import java.util.Arrays;


public final class Hotel {
	
	private static final int TOTAL_COUNT = 20;
	private static final int ROOM_COUNT = 15;
	private static final int PENTHOUSE_COUNT = 5;
	
	private static int occupiedTotal;
	private static int occupiedRooms;
	private static int occupiedPenthouses;

	private static HotelRoom[] ROOMS;
	
	Hotel() {
		ROOMS = new HotelRoom[TOTAL_COUNT];
		
		occupiedTotal = 0;
		
	}
	
	//safe copy of the ROOMS array, changing the copy doesn't change the original
	public static HotelRoom[] getRooms() {
		HotelRoom[] ret = Arrays.copyOf(ROOMS, ROOMS.length);
		
		return ret;
	}
	
	public static boolean bookRoom(HotelRoom room) {
		
		HotelRoom.RoomType type = room.getType();
		
		if (occupiedTotal == 20) {
			return false;
		}
		
		if (type == HotelRoom.RoomType.PENTHOUSE && occupiedPenthouses == PENTHOUSE_COUNT) {
			return false;
		}
		
		if (type == HotelRoom.RoomType.REGULAR && occupiedRooms == ROOM_COUNT) {
			return false;
		}
			
		occupiedTotal += 1;
		ROOMS[occupiedTotal - 1] = room;
		
		if (type == HotelRoom.RoomType.PENTHOUSE) {
			occupiedPenthouses += 1;
		}
		else if (type == HotelRoom.RoomType.REGULAR) {
			occupiedRooms += 1;
		}
		
		return true;
		
	}
	
	public boolean freeRoom(HotelRoom room) {
		
		for (HotelRoom hr : ROOMS) {
			
			if (hr.equals(room)) {
				
			}
			
		}
		
		return false;
		
	}
	
}
