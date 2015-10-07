/**
 * @author Matthieu Lepers & Verschaeve Th�o
 * @version 1.0.0
 * @description The room's superclass or just an empty room
 */
package dungeon.rooms;

import java.util.HashMap;
import java.util.Map;

import dungeon.Dungeon;
import dungeon.entity.Player;

public abstract class Room
{
	protected Map<String,Room> neighbours;
	protected String name;
	protected Player player;
	protected Dungeon dungeon;
	
	/**
	 * Room constructor
	 */
	public Room()
	{
		this.neighbours = new HashMap<String,Room>();
		this.name = null;
		this.player = null;
		this.dungeon = null;
	}
	
	/**
	 * EmptyRoom constructor
	 * @param name
	 * 		The room name
	 */
	public Room(String name, Player player, Dungeon d)
	{
		this.neighbours = new HashMap<String,Room>();
		this.name = name;
		this.player = player;
		this.dungeon = d;
	}
	
	/* ----- Getters ----- */
	/**
	 * Get the room's name
	 * @return the room's name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Get the room neighbours
	 * @return the room neighbours
	 */
	public Map<String,Room> getNeighbours()
	{
		return this.neighbours;
	}
	
	/**
	 * Get the next room to go with a direction
	 * @param direction
	 * 		The string direction
	 * @return The next room to go if the direction is in the neighbours Map, this room else
	 */
	public Room getNextRoom(String direction)
	{
		if (neighbours.containsKey(direction))
		{
			Room r = this.neighbours.get(direction);
			System.out.println("Enterring a " + r.getName());
			return this.neighbours.get(direction);
		}
		else
		{
			System.out.println("No room in this direction !");
			return this;
		}
	}
	
	/**
	 * Get the player who is in this room
	 * @return the player
	 */
	public Player getPlayer()
	{
		return this.player;
	}
	
	/**
	 * Get the room's dungeon
	 * @return the room's dungeon
	 */
	public Dungeon getDungeon()
	{
		return this.dungeon;
	}
	
	/* ----- Setters ----- */
	/**
	 * Set the new neighbours for this room
	 * @param neighbours
	 * 		The new neighbours
	 */
	public void setNeighbours(Map<String,Room> neighbours)
	{
		this.neighbours = neighbours;
	}
	
	/**
	 * Add a room with her direction in the neighbours Map
	 * @param r
	 * 		The room to add
	 * @param direction
	 * 		The direction
	 */
	public void addNextRoom(Room r, String direction)
	{
		this.neighbours.put(direction, r);
	}
	
	/**
	 * Set the room name
	 * @param name
	 * 		The room name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Set the room player
	 * @param p
	 * 		The room player
	 */
	public void setPlayer(Player p)
	{
		this.player = p;
	}
	
	/**
	 * Set the dungeon
	 * @param d
	 * 		The dungeon
	 */
	public void setDungeon(Dungeon d)
	{
		this.dungeon = d;
	}
	
	/* ----- Booleans ----- */
	/**
	 * Test if the neighbours map has the direction 'direction'
	 * @param direction
	 * 		The direction to test
	 * @return <code>true</code> if the map contains the direction 'direction, false else
	 */
	public boolean hasDirection(String direction)
	{
		return this.neighbours.containsKey(direction);
	}
	
	/**
	 * Check if this room has empty neightbours
	 * @return <code>true</code> if the neighbours map is empty, false else
	 */
	public boolean neighboursIsEmpty()
	{
		return this.neighbours.keySet().isEmpty();
	}
	
	/* ----- Actions ----- */
	public void entering() throws InstantiationException, IllegalAccessException
	{
		System.out.println("You enter the room " + this.getName() + " ...");
	}
	
	/* ----- Printers ----- */
	/**
	 * Print in the console a description of this room
	 */
	public void getDescription()
	{
		System.out.println("This room is empty ...");
	}
}