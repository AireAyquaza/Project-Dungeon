/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents a DarkRoom where you need to use a spell or item
 */
package dungeon.rooms;

import dungeon.Dungeon;
import dungeon.entity.Player;

public class DarkRoom extends Room
{
	private boolean light = false;
	
	/**
	 * DarkRoom constructor
	 */
	public DarkRoom()
	{
		super();
	}
	
	/**
	 * DarkRoom constructor
	 * @param name
	 * 		The name of this room
	 * @param player
	 * 		The player who can be in
	 * @param d
	 * 		The dungeon who can be part of
	 */
	public DarkRoom(String name, Player player, Dungeon d)
	{
		super(name,player,d);
	}
	
	/* ---- Getters ---- */
	/**
	 * Get the state of the light
	 * @return <code>true</code> if light is ON, false else
	 */
	public boolean getLight()
	{
		return this.light;
	}
	
	/* ---- Setters ---- */
	/**
	 * Set light to ON
	 */
	public void setLight()
	{
		this.light = true;
		this.player.setTraped(false);
		System.out.println("You can now see all the room!");
	}
	
	/* ---- Actions ---- */
	/**
	 * What happening when a player entering in this room:
	 * You need to light ON this room
	 */
	@Override
	public void entering()
	{
		if (!this.light)
		{
			System.out.println("It's too dark here ... You can't see anything ...");
			this.player.setTraped(true);
		}
		else
		{
			System.out.println("No more Darkness here ...");
		}
	}
	
	/**
	 * Print in the console a description of this room
	 */
	@Override
	public void getDescription()
	{
		if (!this.light)
		{
			System.out.println("How do you describe a room completely in the dark?");
		}
		else
		{
			super.getDescription();
		}
	}
}
