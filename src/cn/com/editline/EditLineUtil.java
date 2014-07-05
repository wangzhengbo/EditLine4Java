package cn.com.editline;

class EditLineUtil {
	private EditLineUtil() {

	}

	public static HistEntry toHistEntry(HIST_ENTRY entry) {
		HistEntry histEntry = null;

		if ((entry != null) && (entry.line != null)) {
			histEntry = new HistEntry(entry.line, entry.timestamp, entry.data);
		}

		return histEntry;
	}

	// private static HIST_ENTRY toHistEntry(HistEntry entry) {
	// HIST_ENTRY histEntry = null;
	//
	// if ((entry != null) && (entry.line != null)) {
	// histEntry = new HIST_ENTRY();
	// histEntry.line = entry.line;
	// histEntry.timestamp = entry.timestamp;
	// histEntry.data = entry.data;
	// }
	//
	// return histEntry;
	// }
}
