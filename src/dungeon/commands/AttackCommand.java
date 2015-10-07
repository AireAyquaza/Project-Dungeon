/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.1
 * @description Represents the Attack command
 */
package dungeon.commands;

import dungeon.entity.EntityLiving;
import dungeon.entity.Player;
import dungeon.rooms.BossRoom;
import dungeon.rooms.MonsterRoom;
import dungeon.rooms.Room;

public class AttackCommand extends Command
{
	/**
	 * AttackCommand constructor
	 */
	public AttackCommand()
	{
		super();
		this.description = "Attack the enemy with your physical strength.";
	}
	
	/**
	 * Execute the command
	 */
	@Override
	public void execute()
	{
		Room currentRoom = this.d.getCurrentRoom();
		Player player = this.d.getPlayer();
		
		if (currentRoom instanceof MonsterRoom)
		{
			EntityLiving e = ((MonsterRoom) currentRoom).getMonster();
			player.attack(e);
			if (!e.isDead())
				e.attack(player);
		}
		else if (currentRoom instanceof BossRoom)
		{
			EntityLiving e = ((BossRoom) currentRoom).getBoss();
			player.attack(e);
			if (!e.isDead())
				e.attack(player);
		}
		else
		{
			System.out.println("There is no monster in this room ...");
		}
	}
}
