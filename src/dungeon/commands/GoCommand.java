/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.1
 * @description Represents the Go command
 */
package dungeon.commands;

import java.util.Arrays;
import java.util.List;

import dungeon.inventory.ItemStack;
import dungeon.inventory.KeyItem;

public class GoCommand extends Command
{
	/**
	 * GoCommand constructor
	 */
	public GoCommand()
	{
		super();
		this.description = "Go in a direction.";
		this.argument = "[direction]";
	}

	/**
	 * The method who execute the command
	 */
	@Override
	public void execute()
	{
		if (!d.getPlayer().isTraped())
		{
			String direction = this.argv.replaceAll("go (.+)", "$1");
			if (direction.equals("locked"))
			{
				List<ItemStack> inv = Arrays.asList(this.d.getPlayer().getInventory());
				ItemStack key = new KeyItem();
				
				if (inv.contains(key))
				{
					int index = inv.indexOf(key);
					this.d.getPlayer().useItem(index, this.d.getCurrentRoom());
					System.out.println("You use a key to open the door!");
					this.d.setCurrentRoom(this.d.getCurrentRoom().getNextRoom(direction));
					try
					{
						this.d.getCurrentRoom().entering();
					}
					catch (InstantiationException | IllegalAccessException e)
					{
						throw new RuntimeException();
					}
				}
				else
				{
					System.out.println("You don't have any key to open this door!");
				}
			}
			else
			{
				this.d.setCurrentRoom(this.d.getCurrentRoom().getNextRoom(direction));
				try
				{
					this.d.getCurrentRoom().entering();
				}
				catch (InstantiationException | IllegalAccessException e)
				{
					throw new RuntimeException();
				}
			}
		}
		else
		{
			System.out.println("You can't use this command now!");
		}
	}
}