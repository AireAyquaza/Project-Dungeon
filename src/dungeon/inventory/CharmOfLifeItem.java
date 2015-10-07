/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents a Charm Of Life item
 */
package dungeon.inventory;

public class CharmOfLifeItem extends ItemStack
{
	/**
	 * CharmOfLifeItem constructor
	 */
	public CharmOfLifeItem()
	{
		super();
		this.meta = 4;
	}
	
	/**
	 * CharmOfLifeItem constructor
	 * @param amount
	 * 		The amount
	 */
	public CharmOfLifeItem(int amount)
	{
		super(amount);
		this.meta = 4;
	}
	
	/* ----- Utils ----- */
	@Override
	public String toString()
	{
		return " (x" + this.getAmount() + ") Charm of Life (+" + this.getMeta() + " max Life)";
	}
}