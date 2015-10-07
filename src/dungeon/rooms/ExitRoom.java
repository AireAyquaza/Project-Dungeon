/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents a ExitRoom, the last room of the game
 */
package dungeon.rooms;

import dungeon.Dungeon;
import dungeon.entity.Player;

public class ExitRoom extends Room
{
	/**
	 * ExitRoom constructor
	 * @param name
	 * 		The room name
	 * @param player
	 * 		The player in the room
	 */
	public ExitRoom(String name, Player player, Dungeon d)
	{
		super(name,player,d);
	}
	
	/* ---- Actions ---- */
	/**
	 * What happening when a player entering in this room:
	 * You win
	 */
	@Override
	public void entering()
	{
		this.dungeon.setWon();
		System.out.println("Congratulations! You finished the dungeon!");
	}
}