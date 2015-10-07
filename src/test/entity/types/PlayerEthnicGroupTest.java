//Tests OK 26/09/2015
package test.entity.types;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dungeon.entity.types.PlayerEthnicGroup;
import dungeon.inventory.spell.Spell;

public class PlayerEthnicGroupTest
{
	private PlayerEthnicGroup Bob;
	
	@Before
	public void init()
	{
		Bob = PlayerEthnicGroup.PYROMAGE;
	}
	
	@Test
	public void constructorTest()
	{
		assertNotNull(Bob);
	}
	
	@Test
	public void getNameTest()
	{
		assertEquals("Balthazar Octavius Barnabe",Bob.getName());
	}
	
	@Test
	public void getLifePointsTest()
	{
		assertEquals(12,Bob.getLifePoints());
	}
	
	@Test
	public void getPsyPointsTest()
	{
		assertEquals(14,Bob.getPsyPoints());
	}
	
	@Test
	public void getStrengthTest()
	{
		assertEquals(3,Bob.getStrength());
	}
	
	@Test
	public void getArmorValueTest()
	{
		assertEquals(5,Bob.getArmorValue());
	}
	
	@Test
	public void getOffensiveSpellsTest()
	{
		List<Spell> spells = Arrays.asList(new Spell[] {Spell.FIRE_BALL,Spell.FLAMETHROWER,Spell.HELL_ON_EARTH});
		
		assertEquals(spells,Bob.getOffensiveSpells());
	}
	
	@Test
	public void getLandSpellsTest()
	{
		List<Spell> spells = Arrays.asList(new Spell[] {Spell.HEAL,Spell.TORCH});
		
		assertEquals(spells,Bob.getLandSpells());
	}
}
