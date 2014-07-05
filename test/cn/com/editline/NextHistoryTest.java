package cn.com.editline;

import org.junit.Assert;
import org.junit.Test;

public class NextHistoryTest extends BaseTest {
	@Test
	public void nextHistory() {
		Assert.assertTrue(editLine.addHistory("one"));
		Assert.assertTrue(editLine.addHistory("two"));
		Assert.assertFalse(editLine.addHistory(""));
		Assert.assertTrue(editLine.addHistory(" "));
		Assert.assertTrue(editLine.addHistory("three"));

		Assert.assertEquals("three", editLine.previousHistory().getLine());
		Assert.assertNull(editLine.nextHistory());
		Assert.assertEquals("three", editLine.previousHistory().getLine());
		Assert.assertEquals(" ", editLine.previousHistory().getLine());
		Assert.assertEquals("three", editLine.nextHistory().getLine());
		Assert.assertEquals(" ", editLine.previousHistory().getLine());
		Assert.assertEquals("two", editLine.previousHistory().getLine());
		Assert.assertEquals("one", editLine.previousHistory().getLine());
		Assert.assertNull(editLine.previousHistory());
	}
}
