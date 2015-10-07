/**
 * @author Matthieu Lepers & Verschaeve Th�o
 * @version 1.0.0
 * @description Represents a BedRoom where you can restore all points of each type
 */
package dungeon.rooms;

import dungeon.Dungeon;
import dungeon.entity.Player;

public class BedRoom extends Room
{
	/**
	 * BedRoom constructor
	 */
	public BedRoom()
	{
		super();
	}
	
	/**
	 * BedRoom constructor
	 * @param name
	 * 		The name of the room
	 * @param player
	 * 		The player who can be in
	 * @param d
	 * 		The dungeon who can be part of
	 */
	public BedRoom(String name, Player player, Dungeon d)
	{
		super(name, player, d);
	}
	
	/* ---- Actions ---- */
	/**
	 * What happening when a player entering in this room:
	 * Restore all points of each type
	 */
	public void entering()
	{
		System.out.println("Oh ... A bed in this room, let's go to sleep ...");
		player.setLifePoints(player.getMaxLifePoints());
		player.setPsyPoints(player.getMaxPsyPoints());
		System.out.println("Your life points and psy points have been restored!");
	}
	
	/* ----- Printers ----- */
	/**
	 * Print in the console a description of this room
	 */
	public void getDescription()
	{
		System.out.println("There is a bed in the center of the room, nothing else ...");
	}
}
