//Tests OK 26/09/2015
package test.rooms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.rooms.BedRoom;
import dungeon.entity.Player;
import dungeon.entity.types.PlayerEthnicGroup;

public class BedRoomTest
{
	private BedRoom br;
	
	@Before
	public void init()
	{
		this.br = new BedRoom("Bed Room",new Player(PlayerEthnicGroup.PYROMAGE),null);
	}
	
	@Test
	public void constructorTest()
	{
		assertNotNull(br);
	}
	
	@Test
	public void enteringTest()
	{
		Player p = br.getPlayer();
		p.hurt(5);
		p.consumePsy(5);
		br.entering();
		
		assertEquals(12,p.getLifePoints());
		assertEquals(14,p.getPsyPoints());
	}
}