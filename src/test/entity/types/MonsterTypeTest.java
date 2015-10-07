//Tests OK 26/09/2015
package test.entity.types;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dungeon.entity.types.BossType;
import dungeon.entity.types.MonsterType;
import dungeon.inventory.spell.Spell;

public class MonsterTypeTest
{
	private MonsterType monster;
	private BossType magical;
	
	@Before
	public void init()
	{
		this.monster = MonsterType.GOBLIN;
		this.magical = BossType.FIRE_DRAGON;
	}
	
	@Test
	public void getNameTest()
	{
		assertEquals("Goblin",monster.getName());
		assertEquals("Alduin",magical.getName());
	}
	
	@Test
	public void getLifePointsTest()
	{
		assertEquals(15,monster.getLifePoints());
		assertEquals(90,magical.getLifePoints());
	}
	
	@Test
	public void getStrengthTest()
	{
		assertEquals(5,monster.getStrength());
		assertEquals(15,magical.getStrength());
	}
	
	@Test
	public void isMagicalTest()
	{
		assertEquals(false,monster.isMagical());
		assertEquals(true,magical.isMagical());
	}
	
	@Test
	public void getPsyPoints()
	{
		assertEquals(-1,monster.getPsyPoints());
		assertEquals(100,magical.getPsyPoints());
	}
	
	@Test
	public void randomSpellTest()
	{
		Spell s = magical.randomSpell();
		assertTrue(magical.getSpells().contains(s));
		assertNotNull(s);
	}
	
	@Test
	public void getSpellsTest()
	{
		List<Spell> spells = Arrays.asList(new Spell[] {Spell.EARTHQUAKE,Spell.FLAMETHROWER,Spell.FIRE_BALL,Spell.HELL_ON_EARTH});
		assertEquals(spells,magical.getSpells());
		assertEquals(new ArrayList<Spell>(),monster.getSpells());
	}
}
