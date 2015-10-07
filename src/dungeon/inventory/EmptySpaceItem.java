/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents an empty space in inventory
 */
package dungeon.inventory;

import dungeon.rooms.Room;

public class EmptySpaceItem extends ItemStack
{
	/**
	 * EmptySpecaItem constructor
	 */
	public EmptySpaceItem()
	{
		super();
		this.meta = -1;
	}
	
	/**
	 * EmptySpecaItem constructor
	 * @param amount
	 * 		The amount
	 */
	public EmptySpaceItem(int amount)
	{
		super(amount);
		this.meta = -1;
	}

	@Override
	public void use(Room r, int index)
	{
		return;
	}
	
	/* ----- Utils ----- */
	@Override
	public String toString()
	{
		return " ";
	}
}
