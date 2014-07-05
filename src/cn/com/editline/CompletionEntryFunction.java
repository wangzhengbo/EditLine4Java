package cn.com.editline;

import com.sun.jna.Callback;

public interface CompletionEntryFunction extends Callback {
	public String callback(String text, int state);
}
