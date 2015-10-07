package dungeon.rooms;

import dungeon.Dungeon;
import dungeon.entity.Player;

public class EmptyRoom extends Room
{
	/**
	 * EmptyRoom constructor
	 */
	public EmptyRoom()
	{
		super();
	}

	/**
	 * EmptyRoom constructor
	 * @param name
	 * 		The room name
	 * @param player
	 * 		The player
	 * @param d
	 * 		The dungeon
	 */
	public EmptyRoom(String name, Player player, Dungeon d)
	{
		super(name, player, d);
	}
}
