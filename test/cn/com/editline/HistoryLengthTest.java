package cn.com.editline;

import org.junit.Assert;
import org.junit.Test;

public class HistoryLengthTest extends BaseTest {
	@Test
	public void historyLength() {
		Assert.assertEquals(0, editLine.historyLength());

		Assert.assertTrue(editLine.addHistory("one"));
		Assert.assertEquals(1, editLine.historyLength());

		Assert.assertTrue(editLine.addHistory("two"));
		Assert.assertEquals(2, editLine.historyLength());

		Assert.assertTrue(editLine.addHistory("three"));
		Assert.assertEquals(3, editLine.historyLength());

		Assert.assertTrue(editLine.addHistory("three"));
		Assert.assertEquals(4, editLine.historyLength());

		Assert.assertTrue(editLine.addHistory("two"));
		Assert.assertEquals(5, editLine.historyLength());

		Assert.assertTrue(editLine.addHistory("one"));
		Assert.assertEquals(6, editLine.historyLength());

		Assert.assertFalse(editLine.addHistory(null));
		Assert.assertEquals(6, editLine.historyLength());

		Assert.assertFalse(editLine.addHistory(""));
		Assert.assertEquals(6, editLine.historyLength());

		Assert.assertTrue(editLine.addHistory(" "));
		Assert.assertEquals(7, editLine.historyLength());

		HistEntry histEntry = editLine.removeHistory(2);
		Assert.assertNotNull(histEntry);
		Assert.assertEquals("three", histEntry.getLine());
		Assert.assertEquals(6, editLine.historyLength());

		histEntry = editLine.removeHistory(5);
		Assert.assertNotNull(histEntry);
		Assert.assertEquals(" ", histEntry.getLine());
		Assert.assertEquals(5, editLine.historyLength());

		histEntry = editLine.removeHistory(1);
		Assert.assertNotNull(histEntry);
		Assert.assertEquals("two", histEntry.getLine());
		Assert.assertEquals(4, editLine.historyLength());

		histEntry = editLine.removeHistory(4);
		Assert.assertNull(histEntry);
		Assert.assertEquals(4, editLine.historyLength());
	}
}
