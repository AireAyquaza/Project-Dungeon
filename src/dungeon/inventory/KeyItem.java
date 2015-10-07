/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents a key item
 */
package dungeon.inventory;

import dungeon.rooms.Room;

public class KeyItem extends ItemStack
{
	/**
	 * KeyItem constructor
	 */
	public KeyItem()
	{
		super();
	}
	
	/**
	 * KeyItem constructor
	 * @param amount
	 * 		The amount
	 */
	public KeyItem(int amount)
	{
		super(amount);
	}
	
	@Override
	/**
	 * Define how to use this item
	 */
	public void use(Room r, int index)
	{
		r.getPlayer().removeOneItemFromInventory(index, this);
	}
	
	/* ----- Utils ----- */
	@Override
	public String toString()
	{
		return " (x" + this.getAmount() + ") Key";
	}
}