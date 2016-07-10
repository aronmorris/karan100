
public final class Hotel {
	
	private static final int TOTAL_COUNT = 20;
	private static final int ROOM_COUNT = 15;
	private static final int PENTHOUSE_COUNT = 5;
	
	private static int occupiedTotal;
	private static int occupiedRooms;
	private static int occupiedPenthouses;

	private static HotelRoom[] ROOMS;
	
	private Hotel() {
		ROOMS = new HotelRoom[TOTAL_COUNT];
		
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
	
}
