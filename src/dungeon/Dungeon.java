/**
 * @author Matthieu Lepers & Verschaeve Th�o
 * @version 1.0.0
 * @description Represents the game
 */
package dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dungeon.commands.Command;
import dungeon.entity.Player;
import dungeon.rooms.BossRoom;
import dungeon.rooms.Direction;
import dungeon.rooms.ExitRoom;
import dungeon.rooms.Room;
import dungeon.rooms.RoomType;

public class Dungeon
{
	private Room currentRoom;
	private List<Room> rooms;
	private Player player;
	private boolean won = false;
	private boolean lost = false;
	private final static Scanner scan = new Scanner(System.in);
	
	/* ----- Getters ----- */
	public Scanner getScanner()
	{
		return Dungeon.scan;
	}
	
	/**
	 * Get the actual room
	 * @return the actual room
	 */
	public Room getCurrentRoom()
	{
		return this.currentRoom;
	}
	
	/**
	 * Get the player
	 * @return the player
	 */
	public Player getPlayer()
	{
		return this.player;
	}
	
	/**
	 * Get the list of rooms
	 * @return the list of rooms
	 */
	public List<Room> getRooms()
	{
		return this.rooms;
	}
	
	/* ----- Setters ----- */
	/**
	 * Set the start or currentRoom for this dungeon
	 * @param r
	 * 		The current room
	 */
	public void setEntrance(Room r)
	{
		this.currentRoom = r;
	}
	
	/**
	 * Set a new currentRoom
	 * @param room
	 * 		The room to set
	 */
	public void setCurrentRoom(Room room)
	{
		this.currentRoom = room;
	}
	
	/**
	 * Set a game won
	 */
	public void setWon()
	{
		this.won = true;
	}
	
	/**
	 * Set a game lost
	 */
	public void setLost()
	{
		this.lost = true;
	}
	
	/**
	 * Set the player who is in the dungeon
	 * @param p
	 * 		The player
	 */
	public void setPlayer(Player p)
	{
		this.player = p;
	}
	
	/**
	 * Set the list of rooms
	 * @param rooms
	 * 		the list of rooms
	 */
	public void setRooms(List<Room> rooms)
	{
		this.rooms = rooms;
	}
	
	/* ----- Booleans ----- */
	/**
	 * Test if the game is lost or not
	 * @return <code>true</code> if game is lost, false else
	 */
	public boolean gameIsLost()
	{
		return this.lost;
	}
	
	/**
	 * Test if the game is won or not
	 * @return <code>true</code> if game is lost, false else
	 */
	public boolean gameIsWon()
	{
		return this.won;
	}
	
	/* ----- Generation ----- */
	/**
	 * Generate all the rooms you can pass in this dungeon
	 * @param entrance
	 * 		The entrance room
	 * @return A list with all room you can pass in this dungeon
	 * @throws IllegalAccessException 
	 * 		Will never appear
	 * @throws InstantiationException
	 * 		Will never appear
	 */
	public List<Room> generateRoomsList(Room entrance, int nbLayers) throws InstantiationException, IllegalAccessException
	{
		List<Room> rooms = new ArrayList<Room>();
		rooms.add(entrance);
		
		int nbRooms = (3 * ((int) Math.pow(2, nbLayers - 1))) - 3;
		
		for (int i = 0; i < nbRooms; i++)
		{
			Room r = RoomType.randomRoom();
			r.setPlayer(this.player);
			r.setDungeon(this);
			rooms.add(r);
		}
		
		return rooms;
	}
	
	/**
	 * Generate a fixed dungeon
	 */
	public void generateRoomLinks(int nbLayers)
	{
		Room bossRoom = new BossRoom("Boss Room", this.player, this);
		Room exit = new ExitRoom("Exit Room", this.player, this);
		
		int nbRooms = (3 * ((int) Math.pow(2, nbLayers - 1)));
		
		//Link the two final rooms
		bossRoom.addNextRoom(exit, Direction.values()[Direction.randomDirection()].name().toString().toLowerCase());
		
		//Root of the tree
		this.currentRoom.addNextRoom(rooms.get(1), "north");
		this.currentRoom.addNextRoom(rooms.get(2), "east");
		this.currentRoom.addNextRoom(rooms.get(3), "west");
		
		int start = 1;
		int offset = 3;
		
		//Tree layer generation
		//One iteration = one tree layer generated
		//calculation: layerRoom(n) = 2 * layerRoom(n-1)
		// with layerRoom(n) = number of rooms at the layer n
		// with n the generated layer
		while ((start + offset) <= (nbRooms / 2))
		{
			for (int i = start; i < (start + offset); i++)
			{
				int d = Direction.randomDirection();
				int d1 = (d + 1) % Direction.values().length;
				rooms.get(i).addNextRoom(rooms.get(i + offset), Direction.values()[d].name().toString().toLowerCase());
				rooms.get(i).addNextRoom(rooms.get(i + (offset + 1)), Direction.values()[d1].name().toString().toLowerCase());
			}
			
			start = start + offset;
			offset = 2 * offset;
		}
		
		//Link all tree leaf to the boss room
		for (int i = start; i < (start + offset); i++)
		{
			int d = Direction.randomDirection();
			rooms.get(i).addNextRoom(bossRoom, Direction.values()[d].name().toString().toLowerCase());
		}
	}
	
	/* ----- Actions ----- */
	/**
	 * Interpret an user inputed command
	 * @param c
	 * 		The command
	 */
	public void interpretCommand(String c)
	{
		String regex = Command.craftRegex();
		String cmd = c.toLowerCase().replaceAll(regex, "$1");
		
		if (Command.COMMANDS.containsKey(cmd))
		{
			String commandArgv = c.replaceAll(regex, "$2");
			
			Command command;
			try
			{
				command = (Command) Command.COMMANDS.get(cmd).newInstance();
				command.setAttributes(commandArgv, this);
				command.execute();
			}
			catch (Exception e)
			{
				//Will never appear
			}
		}
		else
		{
			System.out.println("This command is not implemented!");
		}
	}
}