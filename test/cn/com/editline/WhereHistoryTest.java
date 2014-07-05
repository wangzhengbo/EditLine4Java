package cn.com.editline;

import org.junit.Assert;
import org.junit.Test;

public class WhereHistoryTest extends BaseTest {
	@Test
	public void whereHistory() {
		Assert.assertEquals(0, editLine.whereHistory());
		Assert.assertTrue(editLine.addHistory("one"));
		Assert.assertEquals(1, editLine.whereHistory());
		Assert.assertTrue(editLine.addHistory("two"));
		Assert.assertEquals(2, editLine.whereHistory());
		Assert.assertFalse(editLine.addHistory(""));
		Assert.assertEquals(2, editLine.whereHistory());
		Assert.assertTrue(editLine.addHistory(" "));
		Assert.assertEquals(3, editLine.whereHistory());
		Assert.assertTrue(editLine.addHistory("three"));
		Assert.assertEquals(4, editLine.whereHistory());

		Assert.assertEquals("three", editLine.previousHistory().getLine());
		Assert.assertEquals(3, editLine.whereHistory());
		Assert.assertEquals(" ", editLine.previousHistory().getLine());
		Assert.assertEquals(2, editLine.whereHistory());
		Assert.assertEquals("two", editLine.previousHistory().getLine());
		Assert.assertEquals(1, editLine.whereHistory());
		Assert.assertEquals("one", editLine.previousHistory().getLine());
		Assert.assertEquals(0, editLine.whereHistory());
		Assert.assertNull(editLine.previousHistory());
		Assert.assertEquals(0, editLine.whereHistory());

		Assert.assertEquals("two", editLine.nextHistory().getLine());
		Assert.assertEquals(1, editLine.whereHistory());
		Assert.assertEquals(" ", editLine.nextHistory().getLine());
		Assert.assertEquals(2, editLine.whereHistory());
		Assert.assertEquals("three", editLine.nextHistory().getLine());
		Assert.assertEquals(3, editLine.whereHistory());
		Assert.assertNull(editLine.nextHistory());
		Assert.assertEquals(4, editLine.whereHistory());
	}
}
