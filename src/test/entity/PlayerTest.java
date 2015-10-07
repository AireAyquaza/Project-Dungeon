//Tests OK 26/09/2015
package test.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import dungeon.entity.EntityLiving;
import dungeon.entity.Player;
import dungeon.entity.monster.NormalMonster;
import dungeon.entity.types.MonsterType;
import dungeon.entity.types.PlayerEthnicGroup;
import dungeon.inventory.*;
import dungeon.inventory.spell.Spell;
import dungeon.rooms.BedRoom;
import dungeon.rooms.DarkRoom;
import dungeon.rooms.Room;

public class PlayerTest
{
	Player Bob;
	
	@Before
	public void init()
	{
		Bob = new Player(PlayerEthnicGroup.PYROMAGE);
	}
	
	@Test
	public void constructorTest()
	{
		assertNotNull(Bob);
	}
	
	@Test
	public void getMaxLifePointsTest()
	{
		ItemStack[] items = new ItemStack[] {new CharmOfLifeItem(1)};
		assertEquals(12,Bob.getMaxLifePoints());
		Bob.addToInventory(items);
		assertEquals(16,Bob.getMaxLifePoints());
	}
	
	@Test
	public void getMaxPsyPointsTest()
	{
		assertEquals(14,Bob.getMaxPsyPoints());
	}
	
	@Test
	public void getFactionTest()
	{
		assertEquals(PlayerEthnicGroup.PYROMAGE,Bob.getFaction());
	}
	
	@Test
	public void getInventoryTest()
	{
		assertEquals(Material.values().length - 1,Bob.getInventory().length);
	}
	
	@Test
	public void getPsyPointsTest()
	{
		assertEquals(Bob.getFaction().getPsyPoints(),Bob.getPsyPoints());
	}
	
	@Test
	public void useOffensiveSpellTest()
	{
		EntityLiving spider = new NormalMonster(MonsterType.GIANT_SPIDER);
		Spell s = Bob.getFaction().getOffensiveSpells().get(0);
		
		Bob.useOffensiveSpell(s, spider);
		assertEquals(13,Bob.getPsyPoints());
	}
	
	@Test
	public void useLandSpellTest()
	{
		Bob.hurt(5);
		assertEquals(12,Bob.getLifePoints());
		
		DarkRoom darkroom = new DarkRoom("Dark Room",Bob,null);
		Spell heal = Bob.getFaction().getLandSpells().get(0);
		Spell torch = Bob.getFaction().getOffensiveSpells().get(1);
		
		Bob.useLandSpell(heal, darkroom);
		assertEquals(12,Bob.getLifePoints());
		assertEquals(13,Bob.getPsyPoints());
		
		assertEquals(false,darkroom.getLight());
		Bob.useLandSpell(torch,darkroom);
		assertEquals(true,darkroom.getLight());
		assertEquals(13,Bob.getPsyPoints());
	}
	
	@Test
	public void consumePsyTest()
	{
		Bob.consumePsy(0);
		assertEquals(14,Bob.getPsyPoints());
		assertEquals(12,Bob.getLifePoints());
		assertEquals(false,Bob.isDead());
		
		Bob.consumePsy(14);
		assertEquals(0,Bob.getPsyPoints());
		assertEquals(12,Bob.getLifePoints());
		assertEquals(false,Bob.isDead());
		
		Bob.consumePsy(9);
		assertEquals(0,Bob.getPsyPoints());
		assertEquals(3,Bob.getLifePoints());
		assertEquals(false,Bob.isDead());
		
		Bob.consumePsy(3);
		assertEquals(0,Bob.getPsyPoints());
		assertEquals(0,Bob.getLifePoints());
		assertEquals(true,Bob.isDead());
		
	}
	
	@Test
	public void setTrapedAndIsTrapedTest()
	{
		assertEquals(false,Bob.isTraped());
		Bob.setTraped(true);
		assertEquals(true,Bob.isTraped());
		Bob.setTraped(false);
		assertEquals(false,Bob.isTraped());
	}
	
	@Test
	public void addToInventoryTest()
	{
		ItemStack[] items = new ItemStack[] {new PsyPotionItem(1), new KeyItem(1)};
		assertEquals(1,Bob.getInventory()[1].getAmount());
		assertEquals(new EmptySpaceItem().toString(),Bob.getInventory()[5].toString());
		
		Bob.addToInventory(items);
		
		assertEquals(2,Bob.getInventory()[1].getAmount());
		assertEquals(" (x1) Key",Bob.getInventory()[5].toString());
		assertEquals(1,Bob.getInventory()[5].getAmount());
	}
	
	@Test
	public void useItemTest()
	{
		Room r = new BedRoom();
		r.setPlayer(Bob);
		Bob.hurt(10);
		assertEquals(9,Bob.getLifePoints());
		
		//Use heal potion
		assertEquals(2,Bob.getInventory()[0].getAmount());
		Bob.useItem(1, r);
		assertEquals(12,Bob.getLifePoints());
		assertEquals(1,Bob.getInventory()[0].getAmount());
	}
	
	@Test
	public void removeOneItemFromInventoryTest()
	{
		Bob.removeOneItemFromInventory(0, new HealPotionItem(1));
		Bob.removeOneItemFromInventory(0, new HealPotionItem(1));
		assertEquals(new EmptySpaceItem(),Bob.getInventory()[0]);
		assertEquals(-1,Bob.getInventory()[0].getAmount());
	}
	
	@Test
	public void dieTest()
	{
		Bob.die();
		
		assertEquals(true,Bob.isDead());
	}
}
