package cn.com.editline;

import org.junit.Assert;
import org.junit.Test;

public class ClearHistoryTest extends BaseTest {
	@Test
	public void clearHistory() {
		Assert.assertTrue(editLine.addHistory("one"));
		Assert.assertTrue(editLine.addHistory("two"));
		Assert.assertTrue(editLine.addHistory("three"));
		Assert.assertEquals(3, editLine.historyLength());
		Assert.assertEquals("one", editLine.historyGet(0).getLine());
		Assert.assertNull(editLine.historyGet(3));

		editLine.clearHistory();
		Assert.assertEquals(0, editLine.historyLength());
		Assert.assertNull(editLine.historyGet(0));

		Assert.assertTrue(editLine.addHistory("one"));
		Assert.assertEquals(1, editLine.historyLength());
		Assert.assertEquals("one", editLine.historyGet(0).getLine());
		Assert.assertNull(editLine.historyGet(1));
	}
}
