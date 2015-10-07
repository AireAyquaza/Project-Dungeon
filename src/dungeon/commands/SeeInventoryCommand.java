/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.1
 * @description Represents the SeeInventory command
 */
package dungeon.commands;

public class SeeInventoryCommand extends Command
{
	/**
	 * SeeInventoryCommand constructor
	 */
	public SeeInventoryCommand()
	{
		super();
		this.description = "See all items in your inventory.";
	}
	
	/**
	 * Execute the command
	 */
	@Override
	public void execute()
	{
		this.d.getPlayer().printInventory();
	}
}
