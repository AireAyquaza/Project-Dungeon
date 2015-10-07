/**
 * @author Matthieu Lepers & Verschaeve Th�o
 * @version 1.0.0
 * @description Represent a Monster who can use spells
 */
package dungeon.entity.monster;

import java.util.List;
import java.util.Random;

import dungeon.Color;
import dungeon.entity.EntityLiving;
import dungeon.entity.Player;
import dungeon.entity.types.BossType;
import dungeon.entity.types.MonsterType;
import dungeon.entity.types.Type;
import dungeon.inventory.spell.Spell;

public class MagicalMonster extends NormalMonster
{
	private static final Random r = new Random();
	private int psyPoints;
	
	/**
	 * MagicalMonster constructor
	 * @param monsterType
	 */
	public MagicalMonster(Type monsterType)
	{
		super(monsterType);
		
		this.psyPoints = monsterType.getPsyPoints();
	}
	
	/* ----- Getters ----- */
	/**
	 * Get the amount of psyPoints left
	 * @return amount of psyPoints left
	 */
	public int getPsyPoints()
	{
		return this.psyPoints;
	}
	
	/**
	 * Get the spells who the monster can use
	 * @return the spells
	 */
	public List<Spell> getSpells()
	{
		return ((MonsterType) this.entityType).getSpells();
	}
	
	/* ----- Actions ----- */
	/**
	 * Allow the monster to use a Spell on an entity
	 * @param s
	 * 		The spell to use
	 * @param e
	 * 		The targeted entity
	 */
	private void useSpell(Spell s,EntityLiving e)
	{
		if (this.getPsyPoints() >= s.getPsyConsumtion())
		{
			if (r.nextFloat() <= s.getFailChance())
			{
				System.out.println(this.entityType.getName() + " fail the spell " + s.toString().toLowerCase() + '!');
			}
			else
			{
				int multiplicator = 1;
				System.out.println(this.entityType.getName() + " attacks " + ((Player) e).getFaction().getName() + " with the spell " + s.toString().toLowerCase() + '!');
				if (r.nextFloat() <= s.getCriticalChance())
				{
					multiplicator = 2;
					System.out.println(Color.RED + "-+-+-Critical hit!-+-+-" + Color.RESET);
					e.hurt(s.getDamage() * multiplicator);
				}
				else
				{
					e.hurt(s.getDamage());
				}
			}
			
			this.consumePsy(s.getPsyConsumtion());
		}
		else
		{
			System.out.println(this.entityType.getName() + " has no more psy points!");
		}
	}
	
	/**
	 * Consume 'amount' psyPoints
	 * @param amount
	 * 		The amount of psyPoints
	 */
	public void consumePsy(int amount)
	{ 
		this.psyPoints -= amount;
		System.out.println(this.entityType.getName() + " lost " + amount + " psy points to use a spell!");
		
	}
	
	/**
	 * Attack an entity
	 * @param e
	 * 		The entity
	 */
	@Override
	public void attack(EntityLiving e)
	{
		Random random = new Random();
		Player p = (Player) e;
		if (random.nextFloat() >= 0.8F)
		{
			if (this.entityType == BossType.GOLEM && ((this.lifePoints * 100) / this.entityType.getLifePoints()) <= 25)
			{
				this.useSpell(Spell.HEAL, e);
			}
			else
			{
				this.useSpell(((MonsterType) this.entityType).randomSpell(), e);
			}
		}
		else
		{
			System.out.println(this.entityType.getName() + " attacks " + p.getFaction().getName() + "!");
			p.hurt(this.entityType.getStrength());
		}
	}
}
