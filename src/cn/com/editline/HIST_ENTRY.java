package cn.com.editline;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class HIST_ENTRY extends Structure {
	public String line;
	public String timestamp;
	public String data;

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("line", "timestamp", "data");
	}
}
