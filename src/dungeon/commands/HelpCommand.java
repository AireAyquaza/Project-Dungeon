/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.1
 * @description Represents the Help command
 */
package dungeon.commands;

public class HelpCommand extends Command
{
	/**
	 * HelpCommand constructor
	 */
	public HelpCommand()
	{
		super();
		this.description = "See all the command you can use.";
	}

	/**
	 * Execute the command
	 */
	@Override
	public void execute()
	{
		try
		{
			Command.printCommands();
		}
		catch (Exception e)
		{
			
		}
	}
}
