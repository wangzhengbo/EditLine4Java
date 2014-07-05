package cn.com.editline;

import org.junit.Assert;
import org.junit.Test;

public class AddHistoryTest extends BaseTest {
	@Test
	public void addHistory() {
		Assert.assertEquals(0, editLine.historyLength());

		Assert.assertTrue(editLine.addHistory("one"));
		Assert.assertEquals(1, editLine.historyLength());

		Assert.assertTrue(editLine.addHistory("two"));
		Assert.assertEquals(2, editLine.historyLength());

		Assert.assertFalse(editLine.addHistory(""));
		Assert.assertFalse(editLine.addHistory(null));
		Assert.assertEquals(2, editLine.historyLength());

		Assert.assertTrue(editLine.addHistory(" "));
		Assert.assertEquals(3, editLine.historyLength());

		Assert.assertTrue(editLine.addHistory("three"));
		Assert.assertEquals(4, editLine.historyLength());
	}
}
