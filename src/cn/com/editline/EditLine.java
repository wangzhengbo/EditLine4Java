package cn.com.editline;

import java.io.Closeable;
import java.util.List;

public interface EditLine extends Closeable {
	/**
	 * Adds line to history.
	 * 
	 * @param line
	 * @return Returns true if success, returns false in case of error.
	 */
	public boolean addHistory(String line);

	public void attemptedCompletionFunction(
			AttemptedCompletionFunction attemptedCompletionFunction);

	/**
	 * Clears current history.
	 */
	public void clearHistory();

	public String[] completionMatches(String text,
			CompletionEntryFunction completionEntryFunction);

	/**
	 * Get the current HistEntry object.
	 * 
	 * @return Returns the current HistEntry object.
	 */
	public HistEntry currentHistory();

	/**
	 * May be called after calling readline() for the last time if you do,
	 * remember to call again usingHistory() before calling readline().
	 */
	public void freeHistory();

	/**
	 * Returns the HistEntry object identified by its progressive index
	 * (starting from zero) or null if the index does not exist.
	 * 
	 * @param index
	 *            progressive index (starting from zero)
	 * @return Returns the HistEntry object identified by its progressive index
	 *         (starting from zero) or null if the index does not exist.
	 */
	public HistEntry historyGet(int index);

	/**
	 * Get the current history length.
	 * 
	 * @return Returns the current history length.
	 */
	public int historyLength();

	/**
	 * Returns all HistEntries.
	 * 
	 * @return Returns all HistEntries.
	 */
	public List<HistEntry> historyList();

	/**
	 * Sets the current history offset based on the history entry progressive
	 * index (starting from zero).
	 * 
	 * @param index
	 *            progressive index (starting from zero)
	 * @return Returns true if successful, false if not.
	 */
	public boolean historySetPos(int index);

	/**
	 * Returns the next HistEntry.
	 * 
	 * @return Returns the next HistEntry, or null if the current offset is
	 *         already at the end of history.
	 */
	public HistEntry nextHistory();

	/**
	 * Returns the previous HistEntry.
	 * 
	 * @return Returns the previous HistEntry, or null if the current offset is
	 *         already at the beginning of history.
	 */
	public HistEntry previousHistory();

	/**
	 * main readline method
	 * 
	 * @param prompt
	 * @return Returns null if failed.
	 */
	public String readline(String prompt);

	/**
	 * Removes a history entry identified by its progressive index (starting
	 * from zero).
	 * 
	 * @param index
	 *            progressive index (starting from zero)
	 * @return Returns the old entry, or null if not successful.
	 */
	public HistEntry removeHistory(int index);

	/**
	 * Replaces the string content of a history entry identified by its
	 * progressive index (starting from zero).
	 * 
	 * @param index
	 *            progressive index (starting from zero)
	 * @param line
	 * @return Returns the updated HistEntry or null if not successful.
	 */
	public HistEntry replaceHistoryEntry(int index, String line);

	/**
	 * MUST be called before calling readline() for the first time.
	 * 
	 * 
	 * @return Returns true if successful, fase if not.
	 */
	public boolean usingHistory();

	/**
	 * Returns the current history index.
	 * 
	 * @return Returns the current history index.
	 */
	public int whereHistory();

	/**
	 * Writes history to a text file; if filename is null or empty, then
	 * %APPDATA%\.history is written.
	 * 
	 * @param file
	 * @return Returns true if successful, returns false if failed.
	 */
	public boolean writeHistory(String file);
}
