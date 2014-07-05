package cn.com.editline;

import org.junit.Assert;
import org.junit.Test;

public class ReadlineTest extends BaseTest {
	@Test
	public void readline() {
		String line;
		System.out.println(String.format("%nType exit to quit the %s.%n%n",
				getClass().getSimpleName()));
		while ((line = editLine.readline("prompt>")) != null
				&& !line.equals("exit")) {

		}
		if (line != null) {
			Assert.assertEquals("exit", line);
		}
	}
}
