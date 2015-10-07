//Tests OK 26/09/2015
package test.rooms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.entity.Player;
import dungeon.entity.types.PlayerEthnicGroup;
import dungeon.rooms.DarkRoom;

public class DarkRoomTest
{
	private DarkRoom dr;
	
	@Before
	public void init()
	{
		this.dr = new DarkRoom("Dark Room",new Player(PlayerEthnicGroup.PALADIN),null);
	}
	
	@Test
	public void constructorTest()
	{
		assertNotNull(dr);
	}
	
	@Test
	public void getLightTest()
	{
		assertEquals(false,dr.getLight());
		dr.setLight();
		assertEquals(true,dr.getLight());
	}
	
	@Test
	public void enteringTest()
	{
		assertEquals(false,dr.getLight());
		assertEquals(false,dr.getPlayer().isTraped());
		dr.entering();
		assertEquals(false,dr.getLight());
		assertEquals(true,dr.getPlayer().isTraped());
		dr.getPlayer().useItem(3, dr);
		assertEquals(true,dr.getLight());
		assertEquals(false,dr.getPlayer().isTraped());
	}
}
