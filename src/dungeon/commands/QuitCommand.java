/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.1
 * @description Represents the Quit command
 */
package dungeon.commands;

public class QuitCommand extends Command
{
	/**
	 * GoCommand constructor
	 */
	public QuitCommand()
	{
		super();
		this.description = "Quit the game.";
	}

	/**
	 * The method who execute the command
	 */
	@Override
	public void execute()
	{
		this.d.setLost();
	}
}
