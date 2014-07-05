package cn.com.editline;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class HistoryListTest extends BaseTest {
	@Test
	public void historyList() {
		Assert.assertEquals(0, editLine.historyList().size());

		Assert.assertTrue(editLine.addHistory("one"));
		List<HistEntry> histEntries = editLine.historyList();
		Assert.assertEquals(1, histEntries.size());
		Assert.assertEquals("one", histEntries.get(0).getLine());

		Assert.assertFalse(editLine.addHistory(null));
		histEntries = editLine.historyList();
		Assert.assertEquals(1, histEntries.size());
		Assert.assertEquals("one", histEntries.get(0).getLine());

		Assert.assertFalse(editLine.addHistory(""));
		histEntries = editLine.historyList();
		Assert.assertEquals(1, histEntries.size());
		Assert.assertEquals("one", histEntries.get(0).getLine());

		Assert.assertTrue(editLine.addHistory("two"));
		histEntries = editLine.historyList();
		Assert.assertEquals(2, histEntries.size());
		Assert.assertEquals("one", histEntries.get(0).getLine());
		Assert.assertEquals("two", histEntries.get(1).getLine());

		Assert.assertTrue(editLine.addHistory(" "));
		histEntries = editLine.historyList();
		Assert.assertEquals(3, histEntries.size());
		Assert.assertEquals("one", histEntries.get(0).getLine());
		Assert.assertEquals("two", histEntries.get(1).getLine());
		Assert.assertEquals(" ", histEntries.get(2).getLine());

		Assert.assertTrue(editLine.addHistory("你好"));
		histEntries = editLine.historyList();
		Assert.assertEquals(4, histEntries.size());
		Assert.assertEquals("one", histEntries.get(0).getLine());
		Assert.assertEquals("two", histEntries.get(1).getLine());
		Assert.assertEquals(" ", histEntries.get(2).getLine());
		Assert.assertEquals("你好", histEntries.get(3).getLine());
	}
}
