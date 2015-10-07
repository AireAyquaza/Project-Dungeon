//Tests OK 26/09/2015
package test.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import dungeon.entity.EntityLiving;
import dungeon.entity.Player;
import dungeon.entity.monster.NormalMonster;
import dungeon.entity.types.BossType;
import dungeon.entity.types.PlayerEthnicGroup;

public class EntityLivingTest
{
	private EntityLiving e;
	
	@Before
	public void init()
	{
		this.e = new Player(PlayerEthnicGroup.PYROMAGE);
	}
	
	@Test
	public void constructorTest()
	{
		assertNotNull(e);
	}
	
	@Test
	public void getEntityTypeTest()
	{
		assertEquals(PlayerEthnicGroup.PYROMAGE,e.getEntityType());
	}
	
	@Test
	public void getLifePointsTest()
	{
		assertEquals(12,e.getLifePoints());
	}
	
	@Test
	public void getStrengthTest()
	{
		assertEquals(3,e.getStrength());
	}
	
	@Test
	public void setLifePointsTest()
	{
		e.setLifePoints(20);
		assertEquals(12,e.getLifePoints());
	}
	
	@Test
	public void setStrengthTest()
	{
		e.setStrength(20);
		assertEquals(20,e.getStrength());
	}
	
	@Test
	public void isDeadTest()
	{
		assertEquals(false,e.isDead());
		e.die();
		assertEquals(true,e.isDead());
	}
	
	@Test
	public void hurtTest()
	{
		e.hurt(5);
		assertEquals(12,e.getLifePoints());
		assertEquals(false,e.isDead());
		
		e.hurt(19);
		assertEquals(0,e.getLifePoints());
		assertEquals(true,e.isDead());
	}
	
	@Test
	public void attackTest()
	{
		EntityLiving e1 = new NormalMonster(BossType.GOLEM);
		e.attack(e1);
		assertEquals(65,e1.getLifePoints());
		assertEquals(false,e1.isDead());
		
		e.attack(e1);
		e.attack(e1);
		e.attack(e1);
		e.attack(e1);
		e.attack(e1);
		e.attack(e1);
		e.attack(e1);
		e.attack(e1);
		e.attack(e1);
		e.attack(e1);
		e.attack(e1);
		e.attack(e1);
		e.attack(e1);
		assertEquals(0,e1.getLifePoints());
		assertEquals(true,e1.isDead());
	}
	
	@Test
	public void dieTest()
	{
		assertEquals(false,e.isDead());
		e.die();
		assertEquals(true,e.isDead());
	}
}
