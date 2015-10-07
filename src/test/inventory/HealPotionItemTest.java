//Tests OK 26/09/2015
package test.inventory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.entity.Player;
import dungeon.entity.types.PlayerEthnicGroup;
import dungeon.inventory.HealPotionItem;
import dungeon.rooms.EmptyRoom;
import dungeon.rooms.Room;

public class HealPotionItemTest
{
	private HealPotionItem hpi;
	
	@Before
	public void init()
	{
		this.hpi = new HealPotionItem(1);
	}
	
	@Test
	public void useTest()
	{
		Player p = new Player(PlayerEthnicGroup.INQUISITOR);
		Room r = new EmptyRoom("Empty Room",p,null);
		p.hurt(10);
		hpi.use(r, 1);
		assertEquals(p.getMaxLifePoints(),p.getLifePoints());
	}
}