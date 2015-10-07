//Tests OK 26/09/2015
package test.rooms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.rooms.RoomType;
import dungeon.rooms.TreasureRoom;

public class RoomTypeTest
{
	private RoomType rt;
	
	@Before
	public void init()
	{
		this.rt = RoomType.TREASUREROOM;
	}
	
	@Test
	public void getRoomClassTest()
	{
		assertEquals(TreasureRoom.class,rt.getRoomClass());
	}
	
	@Test
	public void randomRoomTest() throws InstantiationException, IllegalAccessException
	{
		assertNotNull(RoomType.randomRoom());
	}
}