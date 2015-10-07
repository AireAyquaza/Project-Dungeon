/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represent a Player entity
 */
package dungeon.entity;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import dungeon.Color;
import dungeon.entity.monster.Monster;
import dungeon.entity.types.PlayerEthnicGroup;
import dungeon.entity.types.Type;
import dungeon.inventory.*;
import dungeon.inventory.spell.Spell;
import dungeon.rooms.*;

public class Player extends EntityLiving
{
	private PlayerEthnicGroup faction;
	private ItemStack[] inventory;
	private int psyPoints;
	private boolean traped = false;
	private static final Random r = new Random();
	
	/**
	 * Player constructor
	 * Select the type of the player with his number
	 * If have a problem, select a random type of player
	 * @param entityType
	 * 		The player ethnic group
	 */
	public Player(Type entityType)
	{
		super(entityType);
		
		this.faction = (PlayerEthnicGroup) entityType;
		this.inventory = createDeffaultInventory(Material.values().length - 1);
		
		this.psyPoints = this.faction.getPsyPoints();
		this.setStrength(this.faction.getStrength());
	}
	
	private ItemStack[] createDeffaultInventory(int size)
	{
		ItemStack[] inventory = new ItemStack[size];
		
		for (int i = 0; i < size; i++)
		{
			inventory[i] = new EmptySpaceItem();
		}
		
		inventory[0] = new HealPotionItem(2);
		inventory[1] = new PsyPotionItem(1);
		inventory[2] = new NightVisionPotionItem(3);
		inventory[3] = new SwordItem(1);
		inventory[4] = new ShieldItem(1);
		
		return inventory;
	}
	
	/* ----- Getters ----- */
	/**
	 * Get the Player's faction
	 * @return the player's faction
	 */
	public PlayerEthnicGroup getFaction()
	{
		return this.faction;
	}
	
	/**
	 * Get player's inventory
	 * @return player's inventory
	 */
	public ItemStack[] getInventory()
	{
		return this.inventory;
	}
	
	/**
	 * Get the Player's psy points
	 * @return the player's faction
	 */
	public int getPsyPoints()
	{
		return this.psyPoints;
	}
	
	/**
	 * Get the player max life points
	 * @return the player max life points
	 */
	public int getMaxLifePoints()
	{
		List<ItemStack> inv = Arrays.asList(this.inventory);
		int max  = this.getFaction().getLifePoints();
		
		if (inv.contains(new CharmOfLifeItem()))
		{
			int index = inv.indexOf(new CharmOfLifeItem());
			max += inv.get(index).getAmount() * inv.get(index).getMeta();
		}
		
		return max;
	}
	
	/**
	 * Get the player max psy points
	 * @return the player max psy points
	 */
	public int getMaxPsyPoints()
	{
		List<ItemStack> inv = Arrays.asList(this.inventory);
		int max  = this.getFaction().getPsyPoints();
		
		if (inv.contains(new CharmOfManaItem()))
		{
			int index = inv.indexOf(new CharmOfManaItem());
			max += inv.get(index).getAmount() * inv.get(index).getMeta();
		}
		
		return max;
	}
	
	/* ----- Setters ----- */
	/**
	 * Set a new amount of lifePoints
	 * @param lifePoints
	 * 		The new amount of lifePoints
	 */
	@Override
	public void setLifePoints(int lifePoints)
	{
		if (lifePoints > this.getMaxLifePoints())
		{
			this.lifePoints = this.getMaxLifePoints();
		}
		else
		{
			this.lifePoints = lifePoints;
		}
	}
	
	/**
	 * Set a new amount of psyPoints
	 * @param psyPoints
	 * 		The new amount of psy points
	 */
	public void setPsyPoints(int psyPoints)
	{
		if (psyPoints > this.getMaxPsyPoints())
		{
			this.psyPoints = this.getMaxPsyPoints();
		}
		else
		{
			this.psyPoints = psyPoints;
		}
	}
	
	/**
	 * Set a new state for the trapped attribute
	 * @param b
	 * 		The new state
	 */
	public void setTraped(boolean b)
	{
		this.traped = b;
	}
	
	/* ----- Booleans ---- */
	/**
	 * Test if the player is blocked or traped in a room
	 * @return <code>true</code> if the player canot use command 'go', false else
	 */
	public boolean isTraped()
	{
		return this.traped;
	}
	
	/* ----- Actions ----- */
	/**
	 * Add more items to the player inventory
	 * @param items
	 * 		The items to add into the inventory
	 */
	public void addToInventory(ItemStack[] items)
	{
		List<ItemStack> chestInv = Arrays.asList(items);
		List<ItemStack> playerInv = Arrays.asList(this.inventory);
		Iterator<ItemStack> chestInvIt = chestInv.iterator();
		
		while (chestInvIt.hasNext())
		{
			ItemStack item = chestInvIt.next();
			if (item.getMeta() != -1)
			{
				if (playerInv.contains(item))
				{
					int index = playerInv.indexOf(item);
					this.inventory[index].setAmount(this.inventory[index].getAmount() + 1);
				}
				else
				{
					int index = playerInv.indexOf(new EmptySpaceItem());
					this.inventory[index] = item;
					this.inventory[index].setAmount(item.getAmount());
				}
				System.out.println("You find a" + item.toString().toLowerCase());
			}
		}
	}
	
	/**
	 * Subtract 'brutDamage' player's lifePoints
	 * Can kill player
	 * @param brutDamage
	 * 		The brut damage to apply
	 */
	@Override
	public void hurt(int brutDamage)
	{
		List<ItemStack> inv = Arrays.asList(this.inventory);
		
		// Calculate damage reduction with player armor value and, if player has, shield value
		int damageReduce  = 0;
		if (inv.contains(new ShieldItem()))
		{
			int index = inv.indexOf(new ShieldItem());
			damageReduce = inv.get(index).getAmount() * inv.get(index).getMeta();
		}
		
		int netDamages = (brutDamage - (this.faction.getArmorValue() + damageReduce)) <= 0 ? 0 : (brutDamage - (this.faction.getArmorValue() + damageReduce));
		this.setLifePoints(this.getLifePoints() - netDamages);
		
		if (brutDamage != 0)
		{
			System.out.println(this.faction.getName() + " lowers damage with his armor, he lost " + netDamages + " life points!");
		}
		
		if (this.getLifePoints() <= 0)
		{
			this.die();
		}
	}
	
	/**
	 * Attack an entity
	 * @param e
	 * 		The entity
	 */
	@Override
	public void attack(EntityLiving e)
	{
		List<ItemStack> inv = Arrays.asList(this.inventory);
		
		//Caclulate how many damages the player does on the monster with this strength and, if player has, the sword  damage value
		int damages  = this.getStrength();
		if (inv.contains(new SwordItem()))
		{
			int index = inv.indexOf(new SwordItem());
			damages += inv.get(index).getAmount() * inv.get(index).getMeta();
		}
		
		Monster m = (Monster) e;
		System.out.println(this.faction.getName() + " attacks the " + m.getMonsterType().getName() + "!");
		
		m.hurt(damages);
	}
	
	/**
	 * Use a spell on an entity
	 * @param s
	 * 		The spell
	 * @param e
	 * 		The entity
	 */
	public void useOffensiveSpell(Spell s, EntityLiving e)
	{
		if (r.nextFloat() <= s.getFailChance())
		{
			System.out.println(this.faction.getName() + " fail the spell " + s.toString().toLowerCase() + '!');
		}
		else
		{
			int multiplicator = 1;
			if (r.nextFloat() <= s.getCriticalChance())
			{
				multiplicator = 2;
				System.out.println(Color.RED + "-+-+-Critical hit!-+-+-" + Color.RESET);
				e.hurt(s.getDamage() * multiplicator);
			}
			else
			{
				e.hurt(s.getDamage());
			}
		}
		
		this.consumePsy(s.getPsyConsumtion());
	}
	
	/**
	 * Use a land Spell for help you in the room
	 * @param s
	 * 		The Spell
	 * @param r
	 * 		The room
	 */
	public void useLandSpell(Spell s, Room r)
	{
		if (r instanceof DarkRoom && s != Spell.HEAL)
		{
			((DarkRoom) r).setLight();
		}
		if (s == Spell.HEAL)
		{
			this.setLifePoints(this.getLifePoints() + s.getDamage());
		}
		this.consumePsy(s.getPsyConsumtion());
	}
	
	/**
	 * Use the item in position index
	 * @param index
	 * 		The position in inventory
	 * @param r
	 * 		The room
	 */
	public void useItem(int index, Room r)
	{
		try
		{
			ItemStack item = this.getInventory()[index - 1];
			item.use(r, index - 1);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("There is no item in this slot!");
		}
	}

	/**
	 * Subtract 1 or remove the item 'item' in position 'index' from the inventory
	 * @param index
	 * 		The item position in inventory
	 * @param item
	 * 		The item to apply modification
	 */
	public void removeOneItemFromInventory(int index, ItemStack item)
	{
		item.setAmount(item.getAmount() - 1);
		if (item.getAmount() == 0)
		{
			this.getInventory()[index] = new EmptySpaceItem();
			this.getInventory()[index].setAmount(-1);
		}
	}
	
	/**
	 * Consume 'amount' psyPoints, if player haven't psyPoints, he lost lifePoints
	 * Can kill the player
	 * @param amount
	 * 		The amount of psyPoints
	 */
	public void consumePsy(int amount)
	{
		if (this.psyPoints >= amount)
		{
			this.psyPoints -= amount;
			System.out.println(this.faction.getName() + " lost " + amount + " psy points to use this spell!");
		}
		else
		{
			if (this.psyPoints > 0)
			{
				int lost = amount - this.psyPoints;
				this.setLifePoints(this.getLifePoints() - lost); 
				this.psyPoints = 0;
				System.out.println(this.faction.getName() + " lost " + amount + " psy points to use a spell and lost " + lost + " life points because he has no more psy to use this spell!");
			}
			else
			{
				int lost = this.getLifePoints() - amount;
				this.setLifePoints(lost);
				System.out.println(this.faction.getName() + " lost " + amount + " life points because he has no more psy to use this spell!");
			}
		}
		
		if (this.getLifePoints() == 0)
		{
			this.die();
		}
	}
	
	/**
	 * Kill the player
	 */
	@Override
	public void die()
	{
		super.die();
		System.out.println(this.faction.getName() + " died!");
	}
	
	/* ----- Printers ----- */
	/**
	 * Print the player inventory in console
	 */
	public void printInventory()
	{
		for (int i = 0; i < this.inventory.length; i++)
		{
			this.inventory[i].printItem(i);
		}
	}
	
	/**
	 * Print the status of the player in console
	 */
	public void printStatus()
	{
		List<ItemStack> inv = Arrays.asList(this.inventory);
		System.out.println("Life points: " + this.lifePoints + "/" + this.getMaxLifePoints());
		System.out.println("Psy points: " + this.psyPoints + "/" + this.getMaxPsyPoints());
		
		ItemStack shield = new ShieldItem();
		ItemStack sword = new SwordItem();
		
		int strength = this.strength;
		if (inv.contains(sword))
		{
			int index = inv.indexOf(sword);
			strength += inv.get(index).getAmount() * sword.getMeta();
		}
		System.out.println("Strength: " + strength);
		int armorValue = this.faction.getArmorValue();
		if (inv.contains(new ShieldItem()))
		{
			int index = inv.indexOf(shield);
			armorValue += inv.get(index).getAmount() * shield.getMeta();
		}
		System.out.println("Armor value: " + armorValue);
	}
	
	/**
	 * Print all availables spells for this player
	 */
	public void printSpells()
	{
		Iterator<Spell> it = this.faction.getOffensiveSpells().iterator();
		if (it.hasNext())
		{
			System.out.println("List of Offensives Spells:");
		}
		
		int i = 1;
		
		while (it.hasNext())
		{
			Spell s = it.next();
			System.out.println(String.valueOf(i) + ": " + s.toString().toLowerCase() + " - " + " Power: " + s.getDamage() + " Psy needed: " + s.getPsyConsumtion());
			i++;
		}
		
		Iterator<Spell> it2 = this.faction.getLandSpells().iterator();
		if (it2.hasNext())
		{
			System.out.println("\nList of Lands Spells:");
		}
		
		int i2 = 1;
		
		while (it2.hasNext())
		{
			Spell s = it2.next();
			System.out.println(String.valueOf(i2) + ": " + s.toString().toLowerCase() + " - " + (s.getDamage() != 0 ? " Heal: " + s.getDamage() : "") + " Psy needed: " + s.getPsyConsumtion());
			i2++;
		}
	}
}