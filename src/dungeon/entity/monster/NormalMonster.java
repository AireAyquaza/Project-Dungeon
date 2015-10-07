/**
 * @author Matthieu Lepers & Verschaeve Th�o
 * @version 1.0.0
 * @description Represents Monster who canot use spells
 */
package dungeon.entity.monster;

import dungeon.entity.EntityLiving;
import dungeon.entity.Player;
import dungeon.entity.types.Type;

public class NormalMonster extends Monster
{
	/**
	 * NormalMonster constructor
	 * @param monsterType
	 * 		A MonsterType non-magical
	 */
	public NormalMonster(Type monsterType)
	{
		super(monsterType);
	}
	
	/* ----- Actions ----- */
	/**
	 * Hurt the NormalMonster
	 * @param damage
	 * 		The amount of damage
	 */
	@Override
	public void hurt(int damage)
	{
		this.setLifePoints(this.getLifePoints() - damage);
		
		System.out.println(this.entityType.getName() + " lost " + damage + " life points!");
		
		if (this.getLifePoints() <= 0)
		{
			this.die();
		}
	}
	
	/**
	 * Attack an entity
	 * @param e
	 * 		The entity
	 */
	@Override
	public void attack(EntityLiving e)
	{
		Player p = (Player) e;
		System.out.println(this.entityType.getName() + " attacks " + p.getFaction().getName() + "!");
		p.hurt(this.entityType.getStrength());
	}
	
	/**
	 * Kill the monster
	 */
	@Override
	public void die()
	{
		super.die();
		
		System.out.println(this.entityType.getName() + " died!");
	}
}
