/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.1
 * @description Represents the UseItem command
 */
package dungeon.commands;

import dungeon.rooms.Room;

public class UseItemCommand extends Command
{
	/**
	 * UseItemCommand constructor
	 */
	public UseItemCommand()
	{
		super();
		this.description = "Use an item with his index in your character inventory.";
		this.argument = "[inventory index]";
	}
	
	/**
	 * The method who execute the command
	 */
	@Override
	public void execute()
	{
		Room currentRoom = this.d.getCurrentRoom();
		
		try
		{
			int index = Integer.parseInt(this.argv.replaceAll("uses item ([0-9]+)", "$1"));
			currentRoom.getPlayer().useItem(index, currentRoom);
		}
		catch (NumberFormatException e)
		{
			System.out.println("There is no item in this slot!");
		}
	}
}