/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.1
 * @description Represents the Status command
 */
package dungeon.commands;

public class StatusCommand extends Command
{
	/**
	 * DescribeCommand constructor
	 */
	public StatusCommand()
	{
		super();
		this.description = "See your character status.";
	}
	
	/**
	 * Execute the command
	 */
	@Override
	public void execute()
	{
		this.d.getPlayer().printStatus();
	}
}
