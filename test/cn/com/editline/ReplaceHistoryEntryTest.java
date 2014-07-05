package cn.com.editline;

import org.junit.Assert;
import org.junit.Test;

public class ReplaceHistoryEntryTest extends BaseTest {
	@Test
	public void replaceHistoryEntry() {
		Assert.assertTrue(editLine.addHistory("one"));
		Assert.assertTrue(editLine.addHistory("two"));
		Assert.assertTrue(editLine.addHistory("three"));
		Assert.assertEquals(3, editLine.historyLength());

		Assert.assertNull(editLine.replaceHistoryEntry(4, "four"));
		Assert.assertEquals(3, editLine.historyLength());
		Assert.assertEquals("one", editLine.historyGet(0).getLine());
		Assert.assertEquals("two", editLine.historyGet(1).getLine());
		Assert.assertEquals("three", editLine.historyGet(2).getLine());

		Assert.assertEquals("four", editLine.replaceHistoryEntry(2, "four")
				.getLine());
		Assert.assertEquals(3, editLine.historyLength());
		Assert.assertEquals("one", editLine.historyGet(0).getLine());
		Assert.assertEquals("two", editLine.historyGet(1).getLine());
		Assert.assertEquals("four", editLine.historyGet(2).getLine());

		Assert.assertEquals("Two", editLine.replaceHistoryEntry(1, "Two")
				.getLine());
		Assert.assertEquals(3, editLine.historyLength());
		Assert.assertEquals("one", editLine.historyGet(0).getLine());
		Assert.assertEquals("Two", editLine.historyGet(1).getLine());
		Assert.assertEquals("four", editLine.historyGet(2).getLine());

		Assert.assertNull(editLine.replaceHistoryEntry(0, null));
		Assert.assertEquals(3, editLine.historyLength());
		Assert.assertEquals("one", editLine.historyGet(0).getLine());
		Assert.assertEquals("Two", editLine.historyGet(1).getLine());
		Assert.assertEquals("four", editLine.historyGet(2).getLine());

		Assert.assertNull(editLine.replaceHistoryEntry(0, ""));
		Assert.assertEquals(3, editLine.historyLength());
		Assert.assertEquals("one", editLine.historyGet(0).getLine());
		Assert.assertEquals("Two", editLine.historyGet(1).getLine());
		Assert.assertEquals("four", editLine.historyGet(2).getLine());

		Assert.assertEquals(" ", editLine.replaceHistoryEntry(0, " ").getLine());
		Assert.assertEquals(3, editLine.historyLength());
		Assert.assertEquals(" ", editLine.historyGet(0).getLine());
		Assert.assertEquals("Two", editLine.historyGet(1).getLine());
		Assert.assertEquals("four", editLine.historyGet(2).getLine());
	}
}
