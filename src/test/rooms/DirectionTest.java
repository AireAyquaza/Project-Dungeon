//Tests OK 26/09/2015
package test.rooms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.rooms.Direction;

public class DirectionTest
{
	private Direction d;
	
	@Before
	public void init()
	{
		this.d = Direction.UNDER_THE_CARPET;
	}
	
	@Test
	public void toStringTest()
	{
		assertEquals("under the carpet",d.toString());
	}
	
	@Test
	public void randomDirectionTest()
	{
		int c = Direction.randomDirection();
		boolean b = c >= 0 && c < Direction.values().length;
		assertTrue(b);
	}
}
