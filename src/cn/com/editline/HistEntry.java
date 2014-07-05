package cn.com.editline;

public class HistEntry {
	private String line;
	private String timestamp;
	private String data;

	public HistEntry() {

	}

	public HistEntry(String line, String timestamp, String data) {
		setLine(line);
		setTimestamp(timestamp);
		setData(data);
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("HistEntry(line = %s, timestamp = %s, data = %s)",
				line, timestamp, data);
	}
}
