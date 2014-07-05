package cn.com.editline;

import java.io.IOException;

public class EditLineTest {
	public static void main(String[] args) {
		EditLine editLine = null;
		try {
			editLine = new EditLineImpl();
			String line = null;
			while (((line = editLine.readline("prompt>")) != null)
					&& !"exit".equals(line)) {
				System.out.println("line = " + line);
				editLine.addHistory(line);
			}

			// editLine.writeHistory("history.txt");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (editLine != null) {
				try {
					editLine.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
