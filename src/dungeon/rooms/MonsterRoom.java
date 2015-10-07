/**
 * @author Matthieu Lepers & Verschaeve Th�o
 * @version 1.0.0
 * @description Represents a Monster room
 */
package dungeon.rooms;

import dungeon.Dungeon;
import dungeon.entity.EntityLiving;
import dungeon.entity.Player;
import dungeon.entity.monster.MagicalMonster;
import dungeon.entity.monster.Monster;
import dungeon.entity.monster.NormalMonster;
import dungeon.entity.types.MonsterType;
import dungeon.inventory.ItemStack;

public class MonsterRoom extends Room
{
	private Monster monster;
	private boolean rekt = false;
	
	/**
	 * Monster Room constructor
	 */
	public MonsterRoom()
	{
		super();
		MonsterType mt = MonsterType.getRandomMonster();
		
		if (mt.isMagical())
		{
			this.monster = new MagicalMonster(mt);
		}
		else
		{
			this.monster = new NormalMonster(mt);
		}
	}
	
	/**
	 * MonsterRoom constructor
	 * @param name
	 * 		The room name
	 * @param player
	 * 		The player
	 */
	public MonsterRoom(String name, Player player, Dungeon d)
	{
		super(name,player,d);
		MonsterType mt = MonsterType.getRandomMonster();
		
		if (mt.isMagical())
		{
			this.monster = new MagicalMonster(mt);
		}
		else
		{
			this.monster = new NormalMonster(mt);
		}
	}
	
	/**
	 * Get the monster into this room
	 * @return the monster
	 */
	public EntityLiving getMonster()
	{
		return this.monster;
	}
	
	/* ---- Actions ---- */
	/**
	 * What happening when a player entering in this room:
	 * Launch fight
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
			System.out.println("What is that monster? I think it won't let me pass ...");
			this.player.setTraped(true);
			
			while (!this.monster.isDead())
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
				if (this.monster.isDead())
				{
					Monster m = this.monster;
					ItemStack loot = m.getLoot();
					this.player.setTraped(false);
					System.out.println("The " + m.getMonsterType().getName() + " dropped a" + loot.toString());
					this.player.addToInventory(new ItemStack[] {loot});
					this.rekt = true;
				}
			}
		}
		else
		{
			System.out.println("The " + this.getMonster().getEntityType().getName() + " is dead now I can pass.");
		}
	}
	
	/* ----- Printers ----- */
	/**
	 * Print in the console a description of this room
	 */
	public void getDescription()
	{
		if (this.monster.isDead())
		{
			System.out.println("A " + this.monster.getEntityType().getName() + " standed in the center of this room.");
		}
		else
		{
			System.out.println("A " + this.monster.getEntityType().getName() + " standing in the center of this room.");
		}
	}
}