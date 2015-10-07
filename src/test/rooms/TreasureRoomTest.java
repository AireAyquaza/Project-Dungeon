//Tests OK 26/09/2015
package test.rooms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.Dungeon;
import dungeon.Main;
import dungeon.entity.Player;
import dungeon.entity.types.PlayerEthnicGroup;
import dungeon.rooms.TreasureRoom;

public class TreasureRoomTest
{
	private TreasureRoom tr;
	
	@Before
	public void init() throws InstantiationException, IllegalAccessException
	{
		Dungeon d = new Dungeon();
		Player p = new Player(PlayerEthnicGroup.PYROMAGE);
		d.setPlayer(p);
		Main.createDungeon(d);
		this.tr = new TreasureRoom("Treasure room",p,d);
	}
	
	@Test
	public void constructorTest()
	{
		assertNotNull(tr);
	}
	
	@Test
	public void enteringTest()
	{
		assertEquals(false,tr.treasureIsTake());
		tr.entering();
		assertEquals(true,tr.treasureIsTake());
	}
}