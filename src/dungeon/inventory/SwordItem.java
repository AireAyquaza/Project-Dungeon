/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents a sword item
 */
package dungeon.inventory;

public class SwordItem extends ItemStack
{
	/**
	 * SwordItem constructor
	 */
	public SwordItem()
	{
		super();
		this.meta = 2;
	}
	
	/**
	 * SwordItem constructor
	 * @param amount
	 * 		The amount
	 */
	public SwordItem(int amount)
	{
		super(amount);
		this.meta = 2;
	}
	
	/* ----- Utils ----- */
	@Override
	public String toString()
	{
		return " (x" + this.getAmount() + ") Sword (+" + this.getMeta() + " strength)";
	}
}
