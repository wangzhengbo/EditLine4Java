package cn.com.editline;

import org.junit.Assert;
import org.junit.Test;

public class HistoryGetTest extends BaseTest {
	@Test
	public void historyGet() {
		Assert.assertTrue(editLine.addHistory("one"));
		Assert.assertTrue(editLine.addHistory("two"));
		Assert.assertFalse(editLine.addHistory(null));
		Assert.assertFalse(editLine.addHistory(""));
		Assert.assertTrue(editLine.addHistory(" "));
		Assert.assertTrue(editLine.addHistory("three"));
		Assert.assertTrue(editLine.addHistory("你好"));

		Assert.assertNull(editLine.historyGet(-1));
		Assert.assertNull(editLine.historyGet(5));

		HistEntry histEntry = editLine.historyGet(0);
		Assert.assertNotNull(histEntry);
		Assert.assertEquals("one", histEntry.getLine());

		histEntry = editLine.historyGet(1);
		Assert.assertNotNull(histEntry);
		Assert.assertEquals("two", histEntry.getLine());

		histEntry = editLine.historyGet(2);
		Assert.assertNotNull(histEntry);
		Assert.assertEquals(" ", histEntry.getLine());

		histEntry = editLine.historyGet(4);
		Assert.assertNotNull(histEntry);
		Assert.assertEquals("你好", histEntry.getLine());
	}
}
