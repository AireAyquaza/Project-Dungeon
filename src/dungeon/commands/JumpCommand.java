/**
 * @author Matthieu Lepers & Verschaeve Th�o
 * @version 1.0.1
 * @description Represents the Jump command
 */
package dungeon.commands;

import dungeon.rooms.MonsterRoom;
import dungeon.rooms.Room;
import dungeon.rooms.WellRoom;

public class JumpCommand extends Command
{
	/**
	 * JumpCommand constructor
	 */
	public JumpCommand()
	{
		super();
		this.description = "Just jump ... or more ...";
	}

	/**
	 * Execute the command
	 */
	@Override
	public void execute()
	{
		Room r = this.d.getCurrentRoom();
		
		if (r instanceof WellRoom)
		{
			System.out.println("You fall in the well ...");
			MonsterRoom mr = new MonsterRoom("Monster Cave",this.d.getPlayer(),this.d);
			mr.setNeighbours(this.d.getCurrentRoom().getNeighbours());
			this.d.setCurrentRoom(mr);
			try
			{
				System.out.println("The fall deals you 5 life points and 2 psy points.");
				this.d.getPlayer().setLifePoints(this.d.getPlayer().getLifePoints() - 5);
				this.d.getPlayer().setPsyPoints(this.d.getPlayer().getPsyPoints() - 2);
				mr.entering();
			}
			catch (Exception e)
			{
			}
		}
		else
		{
			System.out.println("You are jumping in the air, are you happy now ?");
		}
	}
}