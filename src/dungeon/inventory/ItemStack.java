/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents an item
 */
package dungeon.inventory;

import dungeon.rooms.Room;

public abstract class ItemStack
{
	protected int amount;
	protected int meta;
	
	/**
	 * Item constructor
	 */
	public ItemStack()
	{
		this.amount = -1;
		this.meta = 0;
	}
	
	/**
	 * Item constructor
	 * @param amount
	 * 		The amount
	 */
	public ItemStack(int amount)
	{
		this.amount = amount;
		this.meta = 0;
	}
	
	/* ----- Getters ----- */
	/**
	 * Get the amount of this item
	 * @return the amount
	 */
	public int getAmount()
	{
		return this.amount;
	}
	
	/**
	 * Get the item meta
	 * @return the item meta
	 */
	public int getMeta()
	{
		return this.meta;
	}
	
	/* ----- Setters ----- */
	/**
	 * Set a new amount for this item
	 * @param amount
	 * 		The new amount
	 */
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	
	/**
	 * Define how to use this item
	 * @param r
	 * 		The room where you use the item
	 * @param index
	 * 		The index in player inventory of the used item
	 */
	public void use(Room r, int index)
	{
		return ;
	}
	
	/* ----- Utils ----- */
	@Override
	public String toString()
	{
		return " ";
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof ItemStack)
		{
			ItemStack item = (ItemStack) o;
			return item.getClass() == this.getClass();
		}
		else
		{
			return false;
		}
	}
	
	/* ----- Printers ----- */
	/**
	 * Print the item in console
	 * @param index
	 * 		The item's index in player's inventory
	 */
	public void printItem(int index)
	{
		System.out.println(String.valueOf(index + 1) + this.toString());
	}
}
