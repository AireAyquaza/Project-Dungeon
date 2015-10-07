/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents all available rooms in the game
 */
package dungeon.rooms;

import java.util.Random;

public enum RoomType
{
	/**
	 * To add a room in the game, enum her here with her class
	 * Do not include entrance room, boss room and exit room
	 */
	BEDROOM(BedRoom.class),
	DARKROOM(DarkRoom.class),
	MONSTERROOM(MonsterRoom.class),
	TREASUREROOM(TreasureRoom.class),
	EMPTYROOM(EmptyRoom.class),
	WELLROOM(WellRoom.class)
	;
	
	RoomType(Class<? extends Room> roomClass)
	{
		this.roomClass = roomClass;
	}
	
	private Class<? extends Room> roomClass;
	
	/**
	 * Get the Room class
	 * @return the room class
	 */
	public Class<? extends Room> getRoomClass()
	{
		return this.roomClass;
	}
	
	/* ---- Statics ---- */
	/**
	 * Get a random Room from the total amount of Rooms
	 * @return a random RoomType
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Room randomRoom() throws InstantiationException, IllegalAccessException
	{
		Random r = new Random();
		
		RoomType type = RoomType.values()[r.nextInt(RoomType.values().length)];
		
		Room room = type.getRoomClass().newInstance();
		room.setName(type.name().toLowerCase());
		return room;
	}
}