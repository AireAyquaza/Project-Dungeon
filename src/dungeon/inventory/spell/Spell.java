/**
 * @author Matthieu Lepers & Verschaeve Th�o
 * @version 1.0.0
 * @description Enum all the implemented Spells
 */
package dungeon.inventory.spell;

public enum Spell
{
	FLAMETHROWER(0,8,0.18F,0.15F),
	ICICLE(0,6,0.18F,0.15F),
	FIRE_BALL(1,12,0.2F,0.05F),
	EARTHQUAKE(3,20,0F,0F),
	SPARK(3, 8, 0.4F, 0.1F),
	THUNDER(4,12,0.6F,0.20F),
	HELL_ON_EARTH(7,15,0.8F,0.05F),
	ICE_AGE(7,15,0.8F,0.05F),
	STONE_EDGE(7,15,0.7F,0.3F),
	LIGHT_ARMOR(2,0,0F,0F),
	HEAL(1,4,0F,0F),
	NIGHT_VISION(1,0,0F,0F),
	TORCH(1,0,0F,0F),
	ADVANCED_HEAL(2, 10, 0F, 0F);
	
	/**
	 * Spell constructor
	 * @param psyConsumption
	 * 		The amount of needed psyPoints to use the spell
	 * @param damage
	 * 		The damages
	 * @param criticalChance
	 * 		The chance to do a critical hit [0-1]
	 * @param failChance
	 */
	Spell(int psyConsumption, int damage, float criticalChance, float failChance)
	{
		this.psyConsumption = psyConsumption;
		this.damage = damage;
		this.criticalChance = criticalChance;
		this.failChance = failChance;
	}
	
	private int psyConsumption;
	private int damage;
	private float criticalChance;
	private float failChance;
	
	/* ----- Getters ----- */
	/**
	 * Get the amount of psy nedded for use this spell
	 * @return the amount of psy
	 */
	public int getPsyConsumtion()
	{
		return this.psyConsumption;
	}
	
	/**
	 * Get the amount of damage the spell do
	 * @return the amount of damage
	 */
	public int getDamage()
	{
		return this.damage;
	}
	
	/**
	 * Get the chance to do a critical hit
	 * @return the chance
	 */
	public float getCriticalChance()
	{
		return this.criticalChance;
	}
	
	/**
	 * Get the chance to fail the spell
	 * @return the chance
	 */
	public float getFailChance()
	{
		return this.failChance;
	}
}