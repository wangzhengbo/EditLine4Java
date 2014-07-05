package cn.com.editline;

import java.util.Arrays;

import org.junit.Test;

public class AttemptedCompletionFunctionTest extends BaseTest {
	@Test
	public void attemptedCompletionFunction() {
		final String[] COMMANDS = new String[] { "cls", "dir", "rm", "rmdir",
				"cp" };

		final CompletionEntryFunction completionEntryFunction = new CompletionEntryFunction() {
			public String callback(String text, int state) {
				System.out.println("#2");
				String value = null;

				for (int i = state; i < COMMANDS.length; i++) {
					if (text.length() == 0 || COMMANDS[i].startsWith(text)) {
						value = COMMANDS[i];
						break;
					}
				}

				System.out.println("value = " + value);

				return value;
			}
		};

		final AttemptedCompletionFunction attemptedCompletionFunction = new AttemptedCompletionFunction() {
			public String[] callback(String text, int start, int end) {
				System.out.println("#1");
				String[] matches = null;
				if (start == 0) {
					matches = editLine.completionMatches(text,
							completionEntryFunction);
					System.out.println("matches = " + Arrays.asList(matches));
				}
				return matches;
			}
		};

		editLine.attemptedCompletionFunction(attemptedCompletionFunction);

		String line = null;
		while (((line = editLine.readline("prompt>")) != null)
				&& !"exit".equals(line)) {
			editLine.attemptedCompletionFunction(attemptedCompletionFunction);
			editLine.addHistory(line);
		}
	}
}
