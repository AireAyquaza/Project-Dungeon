//Tests OK 26/09/2015
package test.rooms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.Dungeon;
import dungeon.Main;
import dungeon.entity.Player;
import dungeon.entity.types.PlayerEthnicGroup;
import dungeon.rooms.ExitRoom;

public class ExitRoomTest
{
	private ExitRoom er;
	
	@Before
	public void init() throws InstantiationException, IllegalAccessException
	{
		Dungeon d = new Dungeon();
		Player p = new Player(PlayerEthnicGroup.PYROMAGE);
		d.setPlayer(p);
		Main.createDungeon(d);
		er = new ExitRoom("Exit Room",p,d);
	}
	
	@Test
	public void constructorTest()
	{
		assertNotNull(er);
	}
	
	@Test
	public void enteringTest()
	{
		assertEquals(false,er.getDungeon().gameIsWon());
		er.entering();
		assertEquals(true,er.getDungeon().gameIsWon());
	}
}
