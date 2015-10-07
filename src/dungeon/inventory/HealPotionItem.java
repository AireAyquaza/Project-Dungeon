/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents a heal potion item
 */
package dungeon.inventory;

import dungeon.rooms.Room;

public class HealPotionItem extends ItemStack
{
	/**
	 * HealPotion constructor
	 */
	public HealPotionItem()
	{
		super();
	}
	
	/**
	 * Item constructor
	 * @param amount
	 */
	public HealPotionItem(int amount)
	{
		super(amount);
	}
	
	@Override
	/**
	 * Define how to use this item
	 */
	public void use(Room r, int index)
	{
		r.getPlayer().setLifePoints(r.getPlayer().getMaxLifePoints());
		System.out.println("You have restored all your life points!");
		r.getPlayer().removeOneItemFromInventory(index, this);
	}
	
	/* ----- Utils ----- */
	@Override
	public String toString()
	{
		return " (x" + this.getAmount() + ") Potion of Healing";
	}
}