/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents a Charm Of Mana item
 */
package dungeon.inventory;

public class CharmOfManaItem extends ItemStack
{
	/**
	 * CharmOfLifeItem constructor
	 */
	public CharmOfManaItem()
	{
		super();
		this.meta = 3;
	}
	
	/**
	 * CharmOfLifeItem constructor
	 * @param amount
	 * 		The amount
	 */
	public CharmOfManaItem(int amount)
	{
		super(amount);
		this.meta = 3;
	}
	
	/* ----- Utils ----- */
	@Override
	public String toString()
	{
		return " (x" + this.getAmount() + ") Charm of Mana (+" + this.getMeta() + " max Psy)";
	}
}
