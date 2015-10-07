/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents a psy potion item
 */
package dungeon.inventory;

import dungeon.rooms.Room;

public class PsyPotionItem extends ItemStack
{
	/**
	 * PsyPotion constructor
	 */
	public PsyPotionItem()
	{
		super();
	}
	
	/**
	 * Item constructor
	 * @param amount
	 */
	public PsyPotionItem(int amount)
	{
		super(amount);
	}
	
	@Override
	public void use(Room r, int index)
	{
		r.getPlayer().setPsyPoints(r.getPlayer().getMaxPsyPoints());
		System.out.println("You have restored all your psy points!");
		r.getPlayer().removeOneItemFromInventory(index, this);
	}
	
	/* ----- Utils ----- */
	@Override
	public String toString()
	{
		return " (x" + this.getAmount() + ") Potion of Psy";
	}
}
