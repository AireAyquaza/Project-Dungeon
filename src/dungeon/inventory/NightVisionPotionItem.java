/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents a night vision potion item
 */
package dungeon.inventory;

import dungeon.rooms.DarkRoom;
import dungeon.rooms.Room;

public class NightVisionPotionItem extends ItemStack
{
	/**
	 * HealPotion constructor
	 */
	public NightVisionPotionItem()
	{
		super();
	}
	
	/**
	 * Item constructor
	 * @param amount
	 */
	public NightVisionPotionItem(int amount)
	{
		super(amount);
	}
	
	@Override
	public void use(Room r, int index)
	{
		if (r instanceof DarkRoom)
		{
			((DarkRoom) r).setLight();
		}
		
		r.getPlayer().removeOneItemFromInventory(index, this);
	}
	
	/* ----- Utils ----- */
	@Override
	public String toString()
	{
		return " (x" + this.getAmount() + ") Potion of Night vision";
	}
}