/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents a shield item
 */
package dungeon.inventory;

public class ShieldItem extends ItemStack
{

	public ShieldItem()
	{
		super();
		this.meta = 2;
	}

	/**
	 * ShieldItem constructor
	 * @param amount
	 * 		
	 */
	public ShieldItem(int amount)
	{
		super(amount);
		this.meta = 2;
	}
	
	/* ----- Utils ----- */
	@Override
	public String toString()
	{
		return " (x" + this.getAmount() + ") Shield (+" + this.getMeta() + " armor value)";
	}
}
