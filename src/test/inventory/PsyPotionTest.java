//Tests OK 26/09/2015
package test.inventory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.entity.Player;
import dungeon.entity.types.PlayerEthnicGroup;
import dungeon.inventory.PsyPotionItem;
import dungeon.rooms.EmptyRoom;
import dungeon.rooms.Room;

public class PsyPotionTest
{
	private PsyPotionItem ppi;
	
	@Before
	public void init()
	{
		this.ppi = new PsyPotionItem(1);
	}
	
	@Test
	public void useTest()
	{
		Player p = new Player(PlayerEthnicGroup.INQUISITOR);
		Room r = new EmptyRoom("Empty Room",p,null);
		p.consumePsy(8);
		ppi.use(r, 2);
		assertEquals(p.getMaxPsyPoints(),p.getPsyPoints());
	}
}