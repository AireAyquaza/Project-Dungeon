/**
 * @author Matthieu Lepers & Verschaeve Th�o
 * @version 1.0.0
 * @description Represents a BossRoom, the last room before the exit
 */
package dungeon.rooms;

import dungeon.Dungeon;
import dungeon.entity.EntityLiving;
import dungeon.entity.Player;
import dungeon.entity.monster.MagicalMonster;
import dungeon.entity.monster.Monster;
import dungeon.entity.types.MonsterType;
import dungeon.inventory.ItemStack;

public class BossRoom extends Room
{
	private Monster boss;
	private boolean rekt;
	
	/**
	 * BossRoom constructor
	 * @param name
	 * 		The name of the room
	 * @param player
	 * 		The player who can be in
	 * @param d
	 * 		The dungeons who can be part of
	 */
	public BossRoom(String name, Player player, Dungeon d)
	{
		super(name, player, d);
		
		this.boss = new MagicalMonster(MonsterType.getRandomBoss());
	}
	
	/**
	 * Get the Room's Boss
	 * @return the boss
	 */
	public EntityLiving getBoss()
	{
		return this.boss;
	}
	
	/* ---- Actions ---- */
	/**
	 * What happening when a player entering in this room:
	 * Launch the boss fight
	 * @throws IllegalAccessException 
	 * 		Will never appear
	 * @throws InstantiationException 
	 * 		Will never appear
	 */
	@Override
	public void entering() throws InstantiationException, IllegalAccessException
	{
		if (!this.rekt)
		{
			System.out.println("Wow! You see the Exit Room behind this monster! It attacks you!");
			this.player.setTraped(true);
			
			//Fight
			while (!this.boss.isDead())
			{
				System.out.println();
				System.out.print("> ");
				System.out.flush();
				String command = this.dungeon.getScanner().nextLine();
				this.dungeon.interpretCommand(command);
				
				if (this.player.isDead())
				{
					break;
				}
				if (this.boss.isDead())
				{
					Monster m = this.boss;
					ItemStack loot = m.getLoot();
					this.player.setTraped(false);
					System.out.println("The " + m.getMonsterType().getName() + " drop a" + loot.toString());
					this.player.addToInventory(new ItemStack[] {loot});
					this.rekt = true;
				}
			}
		}
		else
		{
			System.out.println("The " + this.getBoss().getEntityType().getName() + " is dead now I can pass.");
		}
	}
	
	/* ----- Printers ----- */
	/**
	 * Print in the console a description of this room
	 */
	public void getDescription()
	{
		if (this.boss.isDead())
		{
			System.out.println("It's not important now that this thing is dead.");
		}
		else
		{
			System.out.println("It's not really the time to describe the room!");
		}
	}
}
