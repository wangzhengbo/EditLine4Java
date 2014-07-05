package cn.com.editline;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

public class WriteHistoryTest extends BaseTest {
	@Test
	public void writeHistory() throws IOException {
		Assert.assertTrue(editLine.addHistory("one"));
		Assert.assertFalse(editLine.addHistory(null));
		Assert.assertFalse(editLine.addHistory(""));
		Assert.assertTrue(editLine.addHistory("two"));
		Assert.assertTrue(editLine.addHistory(" "));
		Assert.assertTrue(editLine.addHistory("three"));

		File file = new File("test/writeHistory.txt");
		if (file.exists()) {
			Assert.assertTrue(file.delete());
		}

		try {
			// write history to not exists file
			Assert.assertTrue(editLine.writeHistory(file.getAbsolutePath()));
			Assert.assertEquals(FileUtils.readFileToString(new File(
					"test/writeHistory.tmp")), FileUtils.readFileToString(file));

			// write history to exists file
			Assert.assertTrue(editLine.addHistory("你好"));
			Assert.assertTrue(editLine.writeHistory(file.getAbsolutePath()));
			Assert.assertEquals(FileUtils.readFileToString(new File(
					"test/writeHistory_UTF-8.tmp"), DEFAULT_ENCODIG), FileUtils
					.readFileToString(file, DEFAULT_ENCODIG));
		} finally {
			if (file.exists()) {
				Assert.assertTrue(file.delete());
			}
		}
	}
}
