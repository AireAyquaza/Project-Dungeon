//Tests OK 26/09/2015
package test.rooms;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import dungeon.entity.Player;
import dungeon.entity.types.PlayerEthnicGroup;
import dungeon.rooms.BossRoom;

public class BossRoomTest
{
	private BossRoom br;
	
	@Before
	public void init()
	{
		this.br = new BossRoom("Boss Room",new Player(PlayerEthnicGroup.PYROMAGE),null);
	}
	
	@Test
	public void constructorTest()
	{
		assertNotNull(br);
	}
	
	@Test
	public void getBossTest()
	{
		assertNotNull(br.getBoss());
	}
}