/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.1
 * @description Represents the ShowDirection command
 */
package dungeon.commands;

import java.util.Set;

public class ShowDirectionsCommand extends Command
{
	/**
	 * ShowDirectionCommand constructor
	 */
	public ShowDirectionsCommand()
	{
		super();
		this.description = "Show the directions where you can go.";
	}
	
	/**
	 * Execute the command
	 */
	@Override
	public void execute()
	{
		Set<String> keys = this.d.getCurrentRoom().getNeighbours().keySet();
		
		for (String key : keys)
		{
			System.out.println("You think you can go to the direction " + key.toLowerCase());
		}
	}
}
