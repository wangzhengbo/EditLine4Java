package cn.com.editline;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

public abstract class BaseTest {
	@Rule
	public TestName testName = new TestName();

	protected static final String DEFAULT_ENCODIG = "UTF-8";
	protected EditLine editLine = null;

	@Before
	public void setUp() {
		System.out.println(String.format("--------------------Begin test %s",
				testName.getMethodName()));

		try {
			String editLineClass = System.getProperty("EditLineImpl");
			if ((editLineClass == null) || (editLineClass.trim().length() == 0)) {
				editLine = new EditLineImpl();
			} else {
				editLine = (EditLine) Class.forName(editLineClass)
						.newInstance();
			}
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@After
	public void tearDown() {
		try {
			if (editLine != null) {
				editLine.close();
			}
		} catch (IOException e) {
			Assert.fail();
		} finally {
			System.out.println(String.format(
					" --------------------End   test %s%n",
					testName.getMethodName()));
		}
	}
}
