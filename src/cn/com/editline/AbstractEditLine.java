package cn.com.editline;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.CallbackReferenceHelper;
import com.sun.jna.Pointer;

public abstract class AbstractEditLine implements EditLine {
	private AttemptedCompletionFunction attemptedCompletionFunction = null;
	private CompletionEntryFunction completionEntryFunction = null;

	/**
	 * Adds line to history.
	 * 
	 * @param line
	 * @return Returns true if success, returns false in case of error.
	 */
	public boolean addHistory(String line) {
		return isEmpty(line) ? false : isNotEmpty(add_history(line));
	}

	public void attemptedCompletionFunction(
			AttemptedCompletionFunction attemptedCompletionFunction) {
		this.attemptedCompletionFunction = attemptedCompletionFunction;
		rl_attempted_completion_function().setPointer(
				0,
				CallbackReferenceHelper
						.getFunctionPointer(this.attemptedCompletionFunction));
	}

	/**
	 * Clears current history.
	 */
	public void clearHistory() {
		clear_history();
	}

	public void close() {
		// Do nothing
	}

	public String[] completionMatches(String text,
			CompletionEntryFunction completionEntryFunction) {
		this.completionEntryFunction = completionEntryFunction;
		return rl_completion_matches(text, this.completionEntryFunction);
	}

	/**
	 * Get the current HistEntry object.
	 * 
	 * @return Returns the current HistEntry object.
	 */
	public HistEntry currentHistory() {
		return EditLineUtil.toHistEntry(current_history());
	}

	/**
	 * May be called after calling readline() for the last time if you do,
	 * remember to call again usingHistory() before calling readline().
	 */
	public void freeHistory() {
		free_history();
	}

	/**
	 * Get the current history length.
	 * 
	 * @return Returns the current history length.
	 */
	public int historyLength() {
		int length = history_length();
		int realLength = 0;
		for (int i = 0; i < length; i++) {
			if (historyGet(i) != null) {
				realLength++;
			}
		}

		return realLength;
	}

	/**
	 * Returns the HistEntry object identified by its progressive index
	 * (starting from zero) or null if the index does not exist.
	 * 
	 * @param index
	 *            progressive index (starting from zero)
	 * @return Returns the HistEntry object identified by its progressive index
	 *         (starting from zero) or null if the index does not exist.
	 */
	public HistEntry historyGet(int index) {
		HistEntry histEntry = null;
		if (index >= 0) {
			histEntry = EditLineUtil.toHistEntry(history_get(index));
		}
		return histEntry;
	}

	/**
	 * Returns all HistEntries.
	 * 
	 * @return Returns all HistEntries.
	 */
	public List<HistEntry> historyList() {
		List<HistEntry> histEntries = new ArrayList<HistEntry>();

		int length = historyLength();
		for (int i = 0; i < length; i++) {
			histEntries.add(historyGet(i));
		}

		return histEntries;
	}

	/**
	 * Sets the current history offset based on the history entry progressive
	 * index (starting from zero).
	 * 
	 * @param index
	 *            progressive index (starting from zero)
	 * @return Returns true if successful, false if not.
	 */
	public boolean historySetPos(int index) {
		// int length = historyLength();
		// return ((index >= 0) && (index < length)) ? (editLine
		// .history_set_pos(index) == 1) : false;
		return (history_set_pos(index) == 1);
	}

	/**
	 * Returns the next HistEntry.
	 * 
	 * @return Returns the next HistEntry, or null if the current offset is
	 *         already at the end of history.
	 */
	public HistEntry nextHistory() {
		return EditLineUtil.toHistEntry(next_history());
	}

	/**
	 * Returns the previous HistEntry.
	 * 
	 * @return Returns the previous HistEntry, or null if the current offset is
	 *         already at the beginning of history.
	 */
	public HistEntry previousHistory() {
		// return (whereHistory() <= 0) ? historyGet(0) :
		// EditLineUtil.toHistEntry(editLine
		// .previous_history());
		return EditLineUtil.toHistEntry(previous_history());
	}

	/**
	 * main readline method
	 * 
	 * @param prompt
	 * @return Returns null if failed.
	 */
	public String readline(String prompt) {
		return readline_(defaultString(prompt));
	}

	/**
	 * Removes a history entry identified by its progressive index (starting
	 * from zero).
	 * 
	 * @param index
	 *            progressive index (starting from zero)
	 * @return Returns the old entry, or null if not successful.
	 */
	public HistEntry removeHistory(int index) {
		return EditLineUtil.toHistEntry(remove_history(index));
	}

	/**
	 * Replaces the string content of a history entry identified by its
	 * progressive index (starting from zero).
	 * 
	 * @param index
	 *            progressive index (starting from zero)
	 * @param line
	 * @return Returns the updated HistEntry or null if not successful.
	 */
	public HistEntry replaceHistoryEntry(int index, String line) {
		return isEmpty(line) ? null : EditLineUtil
				.toHistEntry(replace_history_entry(index, line, null));
	}

	/**
	 * MUST be called before calling readline() for the first time.
	 * 
	 * 
	 * @return Returns true if successful, fase if not.
	 */
	public boolean usingHistory() {
		return (using_history() == 0);
	}

	/**
	 * Returns the current history index.
	 * 
	 * @return Returns the current history index.
	 */
	public int whereHistory() {
		return where_history();
	}

	/**
	 * Writes history to a text file; if filename is null or empty, then
	 * %APPDATA%\.history is written.
	 * 
	 * @param file
	 * @return Returns true if successful, returns false if failed.
	 */
	public boolean writeHistory(String file) {
		return write_history(isBlank(file) ? null : file) == 0;
	}

	protected abstract String add_history(String line);

	protected abstract void clear_history();

	protected abstract int history_length();

	protected abstract HIST_ENTRY history_get(int index);

	protected abstract int history_set_pos(int index);

	protected abstract HIST_ENTRY next_history();

	protected abstract HIST_ENTRY previous_history();

	protected abstract Pointer rl_attempted_completion_function();

	protected abstract String[] rl_completion_matches(String text,
			CompletionEntryFunction completionEntryFunction);

	protected abstract HIST_ENTRY current_history();

	protected abstract void free_history();

	protected abstract String readline_(String prompt);

	protected abstract HIST_ENTRY remove_history(int index);

	protected abstract HIST_ENTRY replace_history_entry(int index, String line,
			Pointer dummy);

	protected abstract int using_history();

	protected abstract int where_history();

	protected abstract int write_history(String file);

	protected static boolean isEmpty(String value) {
		return (value == null) || value.length() == 0;
	}

	protected static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	protected static boolean isBlank(String value) {
		return (value == null) || value.trim().length() == 0;
	}

	protected static String defaultString(final String string) {
		return (string == null) ? "" : string;
	}
}
