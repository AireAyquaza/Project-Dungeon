/**
 * @author Matthieu Lepers && Verschaeve Th�o
 * @version 1.0.0
 * @description Represents the four availables ethnic groups
 */
package dungeon.entity.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dungeon.Color;
import dungeon.inventory.spell.Spell;

public enum PlayerEthnicGroup implements Type
{
	INQUISITOR("Theo de Silverberg", 15 ,5, 14, 7, Arrays.asList(new Spell[] {Spell.THUNDER}), Arrays.asList(new Spell[] {Spell.HEAL,Spell.LIGHT_ARMOR}), Color.YELLOW),
	PYROMAGE("Balthazar Octavius Barnabe", 12, 14, 3, 5, Arrays.asList(new Spell[] {Spell.FIRE_BALL,Spell.FLAMETHROWER,Spell.HELL_ON_EARTH}), Arrays.asList(new Spell[] {Spell.HEAL,Spell.TORCH}), Color.RED),
	SEMI_ELEMENTARY("Shinddha Kory", 13, 8, 10, 4, Arrays.asList(new Spell[] {Spell.ICICLE,Spell.ICE_AGE}), new ArrayList<Spell>(), Color.BLUE),
	NAIN("Grunlek Von Krayn", 14, 6, 16, 4, Arrays.asList(new Spell[] {Spell.EARTHQUAKE}), Arrays.asList(new Spell[] {Spell.NIGHT_VISION}), Color.GREEN),
	PALADIN("Viktor Oppenheimer", 16, 16, 2, 7, Arrays.asList(new Spell[] {Spell.THUNDER,Spell.SPARK}), Arrays.asList(new Spell[] {Spell.ADVANCED_HEAL,Spell.LIGHT_ARMOR}), Color.WHITE)
	;
	
	
	/**
	 * PlayerFaction constructor
	 * @param name
	 * 		The name of the player
	 * @param life
	 * 		The amount of lifePoints
	 * @param psy
	 * 		The amount of psyPoints
	 * @param strength
	 * 		The player's strength
	 * @param armorValue
	 * 		The player's armor value
	 * @param offensiveSpells
	 * 		The list of usable offensive Spells
	 * @param landSpells
	 * 		The list of usable land Spells
	 * @param color
	 * 		The character color
	 */
	PlayerEthnicGroup(String name,int life,int psy, int strength, int armorValue, List<Spell> offensiveSpells, List<Spell> landSpells, String c)
	{
		this.name = name;
		this.lifePoints = life;
		this.psyPoints = psy;
		this.strength = strength;
		this.armorValue = armorValue;
		this.offensiveSpells = offensiveSpells;
		this.landSpells = landSpells;
		this.color = c;
	}
	
	private String name;
	private int lifePoints;
	private int psyPoints;
	private int strength;
	private int armorValue;
	private List<Spell> offensiveSpells;
	private List<Spell> landSpells;
	private String color;
	
	/**
	 * Get player's name
	 * @return the name
	 */
	public String getName()
	{
		return this.color + this.name + Color.RESET;
	}
	
	/**
	 * Get the player's lifePoints amount
	 * @return the lifePoints amount
	 */
	public int getLifePoints()
	{
		return this.lifePoints;
	}
	
	/**
	 * Get the player's psyPoints amount
	 * @return the psyPoints amount
	 */
	public int getPsyPoints()
	{
		return this.psyPoints;
	}
	
	/**
	 * Get the player's strength
	 * @return the strength
	 */
	public int getStrength()
	{
		return this.strength;
	}
	
	/**
	 * Get the player's armor value
	 * @return the armor value
	 */
	public int getArmorValue()
	{
		return this.armorValue;
	}
	
	/**
	 * Get the list of offensive Spells the player can use
	 * @return the list
	 */
	public List<Spell> getOffensiveSpells()
	{
		return this.offensiveSpells;
	}
	
	/**
	 * Get the list of land Spells the player can use
	 * @return the list
	 */
	public List<Spell> getLandSpells()
	{
		return this.landSpells;
	}
	
	/**
	 * Make a presentation of a Player
	 */
	public String presentation()
	{
		String s = "--------------------------------------\n";
		s += "Name: " + this.getName() + "\n";
		s += "Ethnic group: " + this.toString() + "\n";
		s += "Life points: " + this.lifePoints + "\n";
		s += "Psy points: " + this.psyPoints + "\n";
		s += "Strength: " + this.strength + "\n";
		s += "Armor value: " + this.armorValue + "\n";
		
		return s;
	}
}