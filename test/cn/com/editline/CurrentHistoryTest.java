package cn.com.editline;

import org.junit.Assert;
import org.junit.Test;

public class CurrentHistoryTest extends BaseTest {
	@Test
	public void currentHistory() {
		Assert.assertNull(editLine.currentHistory());

		Assert.assertTrue(editLine.addHistory("one"));
		Assert.assertNull(editLine.currentHistory());

		Assert.assertTrue(editLine.addHistory("two"));
		editLine.historySetPos(0);
		Assert.assertEquals("one", editLine.currentHistory().getLine());

		Assert.assertTrue(editLine.addHistory("three"));
		editLine.historySetPos(1);
		Assert.assertEquals("two", editLine.currentHistory().getLine());
		editLine.historySetPos(2);
		Assert.assertEquals("three", editLine.currentHistory().getLine());
		editLine.historySetPos(0);
		Assert.assertEquals("one", editLine.currentHistory().getLine());
	}
}
