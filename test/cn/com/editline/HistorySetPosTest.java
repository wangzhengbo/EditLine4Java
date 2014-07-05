package cn.com.editline;

import org.junit.Assert;
import org.junit.Test;

public class HistorySetPosTest extends BaseTest {
	@Test
	public void historySetPos() {
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

		Assert.assertTrue(editLine.historySetPos(1));
		Assert.assertEquals(1, editLine.whereHistory());
		Assert.assertEquals("one", editLine.previousHistory().getLine());
		Assert.assertNull(editLine.previousHistory());

		Assert.assertTrue(editLine.historySetPos(2));
		Assert.assertEquals(2, editLine.whereHistory());
		Assert.assertEquals("two", editLine.previousHistory().getLine());
		Assert.assertEquals("one", editLine.previousHistory().getLine());
		Assert.assertNull(editLine.previousHistory());
	}
}
