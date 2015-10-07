/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represent a living entity like monsters or players
 */
package dungeon.entity;

import dungeon.entity.types.Type;

public abstract class EntityLiving
{
	protected Type entityType;
	protected int lifePoints;
	protected int strength;
	private boolean isDead = false;
	
	/**
	 * EntityLiving constructor
	 * @param entityType
	 * 		The entity Type
	 */
	public EntityLiving(Type entityType)
	{
		this.entityType = entityType;
		this.lifePoints = entityType.getLifePoints();
		this.strength = entityType.getStrength();
	}

	/* ----- Getters ----- */
	/**
	 * Get the entity type
	 * @return the entity type
	 */
	public Type getEntityType()
	{
		return this.entityType;
	}
	
	/**
	 * Get entity lifePoints
	 * @return the life points
	 */
	public int getLifePoints()
	{
		return this.lifePoints;
	}
	
	/**
	 * Get the player's strength
	 * @return the strength
	 */
	public int getStrength()
	{
		return this.strength;
	}
	
	/* ----- Setters ----- */
	/**
	 * Set a new amount of lifePoints
	 * @param life
	 * 		The new amount
	 */
	public void setLifePoints(int life)
	{
		this.lifePoints = life;
	}
	
	/**
	 * Set a new amount of strength
	 * @param strength
	 * 		The new strength
	 */
	public void setStrength(int strength)
	{
		this.strength = strength;
	}
	
	/* ----- Booleans ----- */
	/**
	 * Test if the player is dead
	 * @return <code>true</code> if player's life points are 0, false else
	 */
	public boolean isDead()
	{
		return this.isDead;
	}
	
	/* ----- Actions ----- */
	/**
	 * Hurt this entity with amount of damage
	 * Can cause entity death
	 * @param damage
	 * 		The amount of damage
	 */
	public void hurt(int damage)
	{
		this.lifePoints -= damage;
		this.isDead = (this.lifePoints <= 0);
	}
	
	/**
	 * Attack an entity
	 * @param e
	 * 		The entity
	 */
	public void attack(EntityLiving e)
	{
		e.hurt(this.strength);
	}
	
	/**
	 * Kill the entity
	 */
	public void die()
	{
		this.isDead = true;
	}
}