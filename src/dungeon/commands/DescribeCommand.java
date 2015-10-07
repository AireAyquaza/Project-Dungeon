/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.1
 * @description Represents the Describe command
 */
package dungeon.commands;

public class DescribeCommand extends Command
{
	/**
	 * DescribeCommand constructor
	 */
	public DescribeCommand()
	{
		super();
		this.description = "Describe the room around you.";
	}
	
	/**
	 * Execute the command
	 */
	@Override
	public void execute()
	{
		this.d.getCurrentRoom().getDescription();
	}
}
