package cn.com.editline;

import org.junit.Assert;
import org.junit.Test;

public class PreviousHistoryTest extends BaseTest {
	@Test
	public void previousHistory() {
		Assert.assertTrue(editLine.addHistory("one"));
		Assert.assertTrue(editLine.addHistory("two"));
		Assert.assertFalse(editLine.addHistory(""));
		Assert.assertTrue(editLine.addHistory(" "));
		Assert.assertTrue(editLine.addHistory("three"));

		Assert.assertEquals("three", editLine.previousHistory().getLine());
		Assert.assertEquals(" ", editLine.previousHistory().getLine());
		Assert.assertEquals("two", editLine.previousHistory().getLine());
		Assert.assertEquals("one", editLine.previousHistory().getLine());
		Assert.assertNull(editLine.previousHistory());
	}
}
