/**
 * @author Matthieu Lepers & Verschaeve Th�o
 * @version 1.0.0
 * @description Represents all the bosses you can see in a dungeon
 */
package dungeon.entity.types;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import dungeon.Color;
import dungeon.inventory.spell.Spell;

public enum BossType implements Type
{
	GOLEM("Golem", 70, 12, true, Color.MAGENTA, 100, new Spell[] {Spell.EARTHQUAKE,Spell.HEAL,Spell.STONE_EDGE}),
	FIRE_DRAGON("Alduin", 90, 15, true, Color.RED, 100, new Spell[] {Spell.EARTHQUAKE,Spell.FLAMETHROWER,Spell.FIRE_BALL,Spell.HELL_ON_EARTH}),
	ICE_DRAGON("Odahviing", 90, 15, true, Color.CYAN, 100, new Spell[] {Spell.EARTHQUAKE,Spell.ICICLE,Spell.ICE_AGE}),
	BLUE_EYES_WHITE_DRAGON("Blue-Eyes White Dragon",100,13,true, Color.CYAN,100, new Spell[]{Spell.EARTHQUAKE,Spell.THUNDER,Spell.ICICLE}),
	RED_EYES_BLACK_DRAGON("Red-Eyes Black Dragon",85,10,true, Color.RED,150, new Spell[]{Spell.EARTHQUAKE,Spell.HELL_ON_EARTH,Spell.STONE_EDGE});
	
	/**
	 * BossType constructor
	 * @param name
	 * 		The name of the monster
	 * @param lifePoints
	 * 		The amount of his life points
	 * @param strength
	 * 		The strength of the monster
	 * @param isMagical
	 * 		Set the monster magical
	 * @param color
	 * 		The color of the monster name
	 * @param psyPoints
	 * 		Set the amount of psyPoints for this monster
	 * @param spells
	 * 		Set the spells the monster can use (by consuming psyPoints)
	 */
	BossType(String name,int lifePoints, int strength, boolean isMagical, String color,int psyPoints,Spell[] spells)
	{
		this.name = name;
		this.lifePoints = lifePoints;
		this.strength = strength;
		this.magical = isMagical;
		this.color = color;
		this.psyPoints = psyPoints;
		this.spells = spells;
	}
	
	private String name;
	private int lifePoints;
	private int strength;
	private boolean magical;
	private String color;
	private int psyPoints;
	private Spell[] spells;
	
	/* ----- Getters ----- */
	/**
	 * Get the name of the monster
	 * @return the name
	 */
	@Override
	public String getName()
	{
		return this.color + this.name + Color.RESET;
	}
	
	/**
	 * Get the max life points amount for the monster
	 * @return the amount
	 */
	@Override
	public int getLifePoints()
	{
		return this.lifePoints;
	}
	
	/**
	 * Get the strength of the monster
	 * @return the strength
	 */
	@Override
	public int getStrength()
	{
		return this.strength;
	}
	
	/**
	 * Get the monster psy points
	 * @return the psy points
	 */
	public int getPsyPoints()
	{
		return this.psyPoints;
	}
	
	/**
	 * Get the monster's Spells
	 * @return the monster's spells
	 */
	public List<Spell> getSpells()
	{
		return Arrays.asList(this.spells);
	}
	
	/* ----- Booleans ----- */
	/**
	 * Test if a monster is a MagicalMonster or not
	 * @return <code>true</code> if the monster is a magical monster, false else
	 */
	public boolean isMagical()
	{
		return this.magical;
	}
	
	/**
	 * Test if a monster has psy points
	 * @return <code>true</code> if psy points are not -1, false else
	 */
	public boolean hasPsyPoints()
	{
		return this.psyPoints != -1;
	}
	
	/**
	 * Get a random spell
	 */
	public Spell randomSpell()
	{
		Random random = new Random();
		return this.spells[random.nextInt(this.spells.length)];
	}
}
