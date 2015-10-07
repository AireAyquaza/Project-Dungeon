package dungeon.rooms;

import dungeon.Dungeon;
import dungeon.entity.Player;

public class WellRoom extends Room
{
	/**
	 * WellRoom constructor
	 */
	public WellRoom()
	{
		super();
	}

	/**
	 * TreasureRoom constructor
	 * @param name
	 * 		The room name
	 * @param player
	 * 		The player
	 * @param d
	 * 		The dungeon
	 */
	public WellRoom(String name, Player player, Dungeon d)
	{
		super(name, player, d);
	}
	
	/* ----- Actions ----- */
	/**
	 * What happening when a player entering in this room:
	 * Add chest content into player inventory
	 */
	@Override
	public void entering()
	{
		System.out.println("Why a well is here ? Maybe I can jump into ...");
	}
	
	/* ----- Printers ----- */
	/**
	 * Print in the console a description of this room
	 */
	@Override
	public void getDescription()
	{
		System.out.println("I think I can see the bottom ...");
	}
}
