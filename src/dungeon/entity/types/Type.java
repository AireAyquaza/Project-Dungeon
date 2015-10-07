/**
 * @author Matthieu Lepers & Verschaeve Th�o
 * @version 1.0.0
 * @description Interface for EntityLiving Types
 */
package dungeon.entity.types;

public interface Type
{
	/**
	 * Get the name of the entity
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Get the maximum life points for this entity
	 * @return the life points
	 */
	public int getLifePoints();
	
	/**
	 * Get the strength for this entity
	 * @return the strength
	 */
	public int getStrength();
	
	/**
	 * Get the psy points for this entity
	 * @return the psy points
	 */
	public int getPsyPoints();
	
}