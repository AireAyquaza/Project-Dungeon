/**
 * @author Matthieu Lepers & Verschaeve Th�o
 * @version 1.0.0
 * @description Monster interface
 */
package dungeon.entity.monster;

import dungeon.entity.EntityLiving;
import dungeon.entity.types.MonsterType;
import dungeon.entity.types.Type;
import dungeon.inventory.ItemStack;
import dungeon.inventory.Material;

public abstract class Monster extends EntityLiving
{
	/**
	 * Monster constructor
	 * @param type
	 * 		The type of the monster
	 */
	public Monster(Type type)
	{
		super(type);
	}
	
	/* ----- Getters ----- */
	/**
	 * Get the type of this monster
	 * @return the type
	 */
	public MonsterType getMonsterType()
	{
		return (MonsterType) this.entityType;
	}
	
	/**
	 * Get the item looted when a Monster die
	 * @return the item
	 * @throws IllegalAccessException 
	 * 		Will never appear
	 * @throws InstantiationException 
	 * 		Will never appear
	 */
	public ItemStack getLoot() throws InstantiationException, IllegalAccessException
	{
		ItemStack item = Material.randomItem();
		item.setAmount(1);
		return item;
	}
}