/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.1
 * @description Represents the SeeSpells command
 */
package dungeon.commands;

public class SeeSpellsCommand extends Command
{
	/**
	 * DescribeCommand constructor
	 */
	public SeeSpellsCommand()
	{
		super();
		this.description = "See the list of Spells your character can use";
	}
	
	/**
	 * Execute the command
	 */
	public void execute()
	{
		this.d.getPlayer().printSpells();
	}
}
