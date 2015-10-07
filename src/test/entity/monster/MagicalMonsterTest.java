//Tests OK 26/09/2015
package test.entity.monster;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import dungeon.entity.monster.MagicalMonster;
import dungeon.entity.types.BossType;

public class MagicalMonsterTest
{
	private MagicalMonster m ;
	
	@Before
	public void init()
	{
		this.m = new MagicalMonster(BossType.FIRE_DRAGON);
	}
	
	@Test
	public void constructorTest()
	{
		assertNotNull(m);
	}
	
	@Test
	public void getPsyPointsTest()
	{
		assertEquals(100,m.getPsyPoints());
	}
	
	@Test
	public void getSpellsTest()
	{
		assertNotNull(m.getSpells());
	}
	
	@Test
	public void consumePsyTest()
	{
		m.consumePsy(50);
		assertEquals(50,m.getPsyPoints());
		m.consumePsy(50);
		assertEquals(0,m.getPsyPoints());
	}
}
