/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents all types of ItemStack
 */
package dungeon.inventory;

import java.util.Random;

public enum Material
{
	CHARM_OF_LIFE(CharmOfLifeItem.class),
	CHARM_OF_MANA(CharmOfManaItem.class),
	HEAL_POTION(HealPotionItem.class),
	PSY_POTION(PsyPotionItem.class),
	NIGHT_VISION_POTION(NightVisionPotionItem.class),
	KEY(KeyItem.class),
	SWORD(SwordItem.class),
	SHIELD(ShieldItem.class),
	EMPTY(EmptySpaceItem.class);
	
	/**
	 * ItemStack constructor
	 * @param itemClass
	 * 		The item class
	 */
	Material(Class<? extends ItemStack> itemClass)
	{
		this.itemClass = itemClass;
	}
	
	private Class<? extends ItemStack> itemClass;
	
	/**
	 * Get the item class
	 * @return the item class
	 */
	public Class<? extends ItemStack> getItemClass()
	{
		return this.itemClass;
	}
	
	/* ----- Statics ---- */
	/**
	 * Get a random Material
	 * @return a random material
	 * @throws IllegalAccessException 
	 * 		Will never appear
	 * @throws InstantiationException 
	 * 		Will never appear
	 */
	public static ItemStack randomItem() throws InstantiationException, IllegalAccessException
	{
		Random r = new Random();
		return Material.values()[r.nextInt(Material.values().length - 1)].getItemClass().newInstance();
	}
}