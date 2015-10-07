//Tests OK 23/09/2015
package test.inventory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dungeon.inventory.KeyItem;
import dungeon.inventory.Material;
import dungeon.inventory.SwordItem;

public class MaterialTest
{
	private Material mat;
	private Material sword;
	
	@Before
	public void init()
	{
		this.mat = Material.KEY;
		this.sword = Material.SWORD;
	}
	
	@Test
	public void getItemClassTest()
	{
		assertEquals(KeyItem.class,mat.getItemClass());
		assertEquals(SwordItem.class,sword.getItemClass());
	}
}
