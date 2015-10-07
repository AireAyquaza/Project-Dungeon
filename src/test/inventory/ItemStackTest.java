//Tests OK 26/09/2015
package test.inventory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.inventory.EmptySpaceItem;
import dungeon.inventory.ItemStack;
import dungeon.inventory.KeyItem;
import dungeon.inventory.SwordItem;

public class ItemStackTest
{
	private ItemStack item;
	
	@Before
	public void init()
	{
		this.item = new SwordItem(1);
	}
	
	@Test
	public void constructorTest()
	{
		assertNotNull(item);
	}
	
	@Test
	public void getAmountTest()
	{
		assertEquals(-1,new EmptySpaceItem().getAmount());
		assertEquals(1,item.getAmount());
	}
	
	@Test
	public void getMetaTest()
	{
		assertEquals(2,item.getMeta());
	}
	
	@Test
	public void setAmountTest()
	{
		assertEquals(1,item.getAmount());
		item.setAmount(2);
		assertEquals(2,item.getAmount());
	}
	
	@Test
	public void equalsTest()
	{
		ItemStack item2 = new SwordItem();
		ItemStack item3 = new KeyItem(3);
		
		assertEquals(true,item.equals(item2));
		assertEquals(false,item.equals(item3));
	}
}
