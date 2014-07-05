package cn.com.editline;

import org.junit.Assert;
import org.junit.Test;

public class RemoveHistory extends BaseTest {
	@Test
	public void removeHistory() {
		Assert.assertTrue(editLine.addHistory("one"));
		Assert.assertTrue(editLine.addHistory("two"));
		Assert.assertTrue(editLine.addHistory("three"));
		Assert.assertEquals(3, editLine.historyLength());
		Assert.assertEquals("one", editLine.historyGet(0).getLine());
		Assert.assertEquals("two", editLine.historyGet(1).getLine());
		Assert.assertEquals("three", editLine.historyGet(2).getLine());

		Assert.assertNull(editLine.removeHistory(3));
		Assert.assertEquals(3, editLine.historyLength());
		Assert.assertEquals("one", editLine.historyGet(0).getLine());
		Assert.assertEquals("two", editLine.historyGet(1).getLine());
		Assert.assertEquals("three", editLine.historyGet(2).getLine());

		HistEntry entry = editLine.removeHistory(1);
		Assert.assertEquals("two", entry.getLine());
		Assert.assertEquals(2, editLine.historyLength());
		Assert.assertEquals("one", editLine.historyGet(0).getLine());
		Assert.assertEquals("three", editLine.historyGet(1).getLine());

		Assert.assertEquals("one", editLine.removeHistory(0).getLine());
		Assert.assertEquals(1, editLine.historyLength());
		Assert.assertEquals("three", editLine.historyGet(0).getLine());

		Assert.assertNull(editLine.removeHistory(1));
		Assert.assertEquals("three", editLine.removeHistory(0).getLine());
		Assert.assertEquals(0, editLine.historyLength());
	}
}
