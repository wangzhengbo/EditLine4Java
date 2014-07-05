package cn.com.editline;

import com.sun.jna.Callback;

public interface AttemptedCompletionFunction extends Callback {
	public String[] callback(String text, int start, int end);
}