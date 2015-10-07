/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.1
 * @description Represents the UseSpell command
 */
package dungeon.commands;

import dungeon.entity.EntityLiving;
import dungeon.entity.Player;
import dungeon.inventory.spell.Spell;
import dungeon.rooms.BossRoom;
import dungeon.rooms.MonsterRoom;
import dungeon.rooms.Room;

public class UseSpellCommand extends Command
{
	/**
	 * UseSpellCommand constructor
	 */
	public UseSpellCommand()
	{
		super();
		this.description = "Use a spell with its name in your charatcer's spell list.";
		this.argument = "[Spell name]";
	}

	/**
	 * Execute the command
	 */
	@Override
	public void execute()
	{
		Room currentRoom = d.getCurrentRoom();
		Player player = d.getPlayer();
		
		try
		{
			String spell = this.argv.replaceAll("uses spell (.+)", "$1").toUpperCase();
			spell = spell.replaceAll(" ","_");
			
			useSpell(currentRoom, player, spell);
		}
		catch (IllegalArgumentException e)
		{
			System.out.println("You don't have this spell!");
		}

	}
	
	/**
	 * Use the spell 'spell' by player 'player' in room 'room'
	 * @param currentRoom
	 * 		The room where the spell is use
	 * @param player
	 * 		The player who use the spell
	 * @param spell
	 * 		The spell to use
	 * @throws IllegalArgumentException
	 * 		If the input spell name is not a usable spell
	 */
	private void useSpell(Room currentRoom, Player player, String spell) throws IllegalArgumentException
	{
		//Check if the spell is an offensive spell
		if (player.getFaction().getLandSpells().contains(Spell.valueOf(spell)))
		{
			Spell s = Spell.valueOf(spell);
			player.useLandSpell(s, currentRoom);
		}
		//Check if the spell is a land spell
		else if (player.getFaction().getOffensiveSpells().contains(Spell.valueOf(spell)))
		{
			Spell s = Spell.valueOf(spell);
			if (currentRoom instanceof MonsterRoom)
			{
				EntityLiving e = ((MonsterRoom) currentRoom).getMonster();
				player.useOffensiveSpell(s, e);
				if (!e.isDead())
					e.attack(player);
			}
			else if (currentRoom instanceof BossRoom)
			{
				EntityLiving e = ((BossRoom) currentRoom).getBoss();
				player.useOffensiveSpell(s, e);
				if (!e.isDead())
					e.attack(player);
			}
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

}
