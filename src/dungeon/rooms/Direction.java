/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Enum all availables directions to go
 */
package dungeon.rooms;

import java.util.Random;

public enum Direction
{
	NORTH,
	EAST,
	WEST,
	SOUTH,
	UNDER_THE_CARPET,
	LOCKED;
	
	/* ----- Utils ----- */
	/**
	 * Get the name of the direction in lower case
	 * @return a lower case name of this direction
	 */
	@Override
	public String toString()
	{
		return this.name().toLowerCase().replaceAll("_", " ");
	}
	
	/* ----- Statics ----- */
	/**
	 * Get a random direction
	 * @return a random direction
	 */
	public static int randomDirection()
	{
		Random r = new Random();
		return r.nextInt(Direction.values().length - 1);
	}
}
