//Tests OK 26/09/2015
package test.inventory.spell;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.inventory.spell.Spell;

public class SpellTest
{
	private Spell s;
	
	@Before
	public void init()
	{
		this.s = Spell.HELL_ON_EARTH;
	}
	
	@Test
	public void constructorTest()
	{
		assertNotNull(s);
	}
	
	@Test
	public void getPsyConsumtionTest()
	{
		assertEquals(s.getPsyConsumtion(),7);
	}
	
	@Test
	public void getDamageTest()
	{
		assertEquals(s.getDamage(),15);
	}
	
	@Test
	public void getCriticalChanceTest()
	{
		assertEquals(s.getCriticalChance(),0.8F,0);
	}
	
	@Test
	public void getfailChanceTest()
	{
		assertEquals(s.getFailChance(),0.05F,0);
	}
}
