/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents a Treasure room
 */
package dungeon.rooms;

import dungeon.Dungeon;
import dungeon.entity.Player;
import dungeon.inventory.ItemStack;
import dungeon.inventory.Material;

public class TreasureRoom extends Room
{
	private boolean take = false;
	
	private ItemStack[] treasure;
	
	/**
	 * Treasure constructor
	 * @throws IllegalAccessException 
	 * 		Will never appear
	 * @throws InstantiationException 
	 * 		Will never appear
	 */
	public TreasureRoom() throws InstantiationException, IllegalAccessException
	{
		super();
		this.treasure = new ItemStack[4];
		this.setRandomTreasure();
	}

	/**
	 * TreasureRoom constructor
	 * @param name
	 * 		The room name
	 * @param player
	 * 		The player
	 * @param d
	 * 		The dungeon
	 * @throws IllegalAccessException 
	 * 		Will never appear
	 * @throws InstantiationException 
	 * 		Will never appear
	 */
	public TreasureRoom(String name, Player player, Dungeon d) throws InstantiationException, IllegalAccessException
	{
		super(name, player, d);
		this.treasure = new ItemStack[4];
		this.setRandomTreasure();
	}
	
	/* ---- Getters ---- */
	/**
	 * Get the room's treasure
	 * @return the treasure
	 */
	public ItemStack[] getTreasure()
	{
		return this.treasure;
	}
	
	/* ----- Setters ----- */
	/**
	 * Set a random chest content
	 * @throws IllegalAccessException 
	 * 		Will never appear
	 * @throws InstantiationException 
	 * 		Will never appear
	 */
	private void setRandomTreasure() throws InstantiationException, IllegalAccessException
	{
		this.treasure[0] = Material.randomItem();
		this.treasure[0].setAmount(1);
		this.treasure[1] = Material.randomItem();
		this.treasure[1].setAmount(1);
		this.treasure[2] = Material.randomItem();
		this.treasure[2].setAmount(1);
		this.treasure[3] = Material.randomItem();
		this.treasure[3].setAmount(1);
	}
	
	/* ----- Booleans ----- */
	/**
	 * Check if treasure is take or not
	 * @return <code>true</code> if treasure is take, false else
	 */
	public boolean treasureIsTake()
	{
		return this.take;
	}
	
	/* ----- Actions ----- */
	/**
	 * What happening when a player entering in this room:
	 * Add chest content into player inventory
	 */
	@Override
	public void entering()
	{
		if (!this.take)
		{
			System.out.println("Oh nice, a chest, let's take its content!");
			this.player.addToInventory(this.treasure);
			this.take = true;
		}
	}
	
	/* ----- Printers ----- */
	/**
	 * Print in the console a description of this room
	 */
	@Override
	public void getDescription()
	{
		System.out.println("A chest on a pedestal, the room is very bright after I opened it.");
	}
}
