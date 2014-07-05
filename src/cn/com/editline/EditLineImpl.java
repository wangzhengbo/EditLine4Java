package cn.com.editline;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;

/**
 * 
 * 
 * @author zhengbo.wang
 */
public class EditLineImpl extends AbstractEditLine implements EditLine {
	/* wineditline library name */
	private static final String DLL_LIB_NAME = "edit"
			+ (Platform.is64Bit() ? "_x64" : "");

	/* wineditline library path */
	private static final String DLL_LIB_RESOURCE_PATH = "/"
			+ EditLineImpl.class.getPackage().getName().replaceAll("\\.", "/")
			+ "/lib/";

	private EditLineLibrary editLine = null;
	private NativeLibrary nativeLibrary = null;

	public EditLineImpl() throws Exception {
		// Initialize EditLine
		loadNativeLibrary();
	}

	protected String add_history(String line) {
		return editLine.add_history(line);
	}

	protected void clear_history() {
		editLine.clear_history();
	}

	protected HIST_ENTRY current_history() {
		return editLine.current_history();
	}

	protected void free_history() {
		editLine.free_history();
	}

	protected HIST_ENTRY history_get(int index) {
		return editLine.history_get(index);
	}

	protected int history_length() {
		return editLine.history_length();
	}

	protected int history_set_pos(int index) {
		return editLine.history_set_pos(index);
	}

	protected HIST_ENTRY next_history() {
		return editLine.next_history();
	}

	protected HIST_ENTRY previous_history() {
		return editLine.previous_history();
	}

	protected Pointer rl_attempted_completion_function() {
		return nativeLibrary
				.getGlobalVariableAddress("rl_attempted_completion_function");
	}

	protected String[] rl_completion_matches(String text,
			CompletionEntryFunction completionEntryFunction) {
		return editLine.rl_completion_matches(text, completionEntryFunction);
	}

	protected String readline_(String prompt) {
		return editLine.readline(prompt);
	}

	protected HIST_ENTRY remove_history(int index) {
		return editLine.remove_history(index);
	}

	protected HIST_ENTRY replace_history_entry(int index, String line,
			Pointer dummy) {
		return editLine.replace_history_entry(index, line, dummy);
	}

	protected int using_history() {
		return editLine.using_history();
	}

	protected int where_history() {
		return editLine.where_history();
	}

	protected int write_history(String file) {
		return editLine.write_history(file);
	}

	/**
	 * Unpacking and loading the library into the Java Virtual Machine.
	 * 
	 * @throws Exception
	 */
	private void loadNativeLibrary() throws Exception {
		// Get what the system "thinks" the library name should be.
		String libNativeName = System.mapLibraryName(DLL_LIB_NAME);

		// Slice up the library name.
		int i = libNativeName.lastIndexOf('.');
		String libNativePrefix = libNativeName.substring(0, i) + '_';
		String libNativeSuffix = libNativeName.substring(i);

		// This may return null in some circumstances.
		InputStream libInputStream = EditLineImpl.class
				.getResourceAsStream(DLL_LIB_RESOURCE_PATH.toLowerCase()
						+ libNativeName);
		if (libInputStream == null) {
			throw new IOException("Unable to locate the native library.");
		}

		// Create the temp file for this instance of the library.
		File libFile = File.createTempFile(libNativePrefix, libNativeSuffix);
		libFile.deleteOnExit();

		// Copy edit.dll or edit_x64.dll library to the temp file.
		copyInputStreamToFile(libInputStream, libFile);
		closeQuietly(libInputStream);

		System.load(libFile.getPath());

		Native.setProtected(true);
		editLine = (EditLineLibrary) Native.loadLibrary(libFile.getName(),
				EditLineLibrary.class);
		nativeLibrary = NativeLibrary.getInstance(libFile.getName());
	}

	private static void closeQuietly(final Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				// Ignore exception
			}
		}
	}

	private static void copyInputStreamToFile(final InputStream input,
			final File file) throws IOException {
		OutputStream output = null;
		try {
			output = new FileOutputStream(file);
			int n = 0;
			byte[] buffer = new byte[4 * 1024];
			while ((n = input.read(buffer)) != -1) {
				output.write(buffer, 0, n);
			}
		} finally {
			closeQuietly(output);
		}
	}

	private static interface EditLineLibrary extends Library {
		/**
		 * this function adds line to history; returns a pointer to the newly
		 * added string, or NULL in case of error
		 * 
		 * @param line
		 * @return
		 */
		public String add_history(String line);

		/**
		 * this function clears current history
		 */
		public void clear_history();

		/**
		 * this function returns a pointer to the current HIST_ENTRY structure
		 * 
		 * @return
		 */
		public HIST_ENTRY current_history();

		/**
		 * this function may be called after calling readline() for the last
		 * time if you do, remember to call again using_history() before calling
		 * readline()
		 */
		public void free_history();

		/**
		 * this function frees a history entry
		 * 
		 * @param entry
		 */
		public void free_history_entry(HIST_ENTRY entry);

		/**
		 * this function returns a pointer to the HIST_ENTRY structure
		 * identified by its progressive index (starting from zero) or NULL if
		 * the index i does not exist
		 * 
		 * @param i
		 * @return
		 */
		public HIST_ENTRY history_get(int i);

		/**
		 * this function returns the current history length
		 * 
		 * @return
		 */
		public int history_length();

		/**
		 * this function returns a pointer to the HIST_ENTRY array
		 * 
		 * @return
		 */
		public Pointer history_list();

		/**
		 * this function sets the current history offset based on the history
		 * entry progressive index (starting from zero) returns 1 if successful,
		 * 0 if not
		 * 
		 * @param i
		 * @return
		 */
		public int history_set_pos(int i);

		/**
		 * this function returns a pointer to the next HIST_ENTRY, or NULL if
		 * the current offset is already at the end of history
		 * 
		 * @return
		 */
		public HIST_ENTRY next_history();

		/**
		 * this function returns a pointer to the previous HIST_ENTRY, or NULL
		 * if the current offset is already at the beginning of history
		 * 
		 * @return
		 */
		public HIST_ENTRY previous_history();

		public String[] rl_attempted_completion_function(
				AttemptedCompletionFunction attemptedCompletionFunction);

		public String[] rl_completion_matches(String text,
				CompletionEntryFunction completionEntryFunction);

		/**
		 * this function reads history from a text file; if filename is a NULL
		 * pointer, then %APPDATA%\.history is searched returns 0 if successful,
		 * -1 or errno if not
		 * 
		 * @return
		 */
		public int read_history(String filename);

		/**
		 * main readline function
		 * 
		 * @param prompt
		 * @return
		 */
		public String readline(String prompt);

		/**
		 * this function removes a history entry identified by its progressive
		 * index (starting from zero) returns the old entry so the user can free
		 * the memory allocated to the string and to the HIST_ENTRY structure
		 * itself, or NULL if not successful
		 * 
		 * @param i
		 * @return
		 */
		public HIST_ENTRY remove_history(int i);

		/**
		 * this function replaces the string content of a history entry
		 * identified by its progressive index (starting from zero) the
		 * histdata_t is ignored and kept only for compatibility reasons returns
		 * a pointer to the updated HIST_ENTRY or NULL if not successful
		 * 
		 * @param i
		 * @param line
		 * @param dummy
		 * @return
		 */
		public HIST_ENTRY replace_history_entry(int i, String line,
				Pointer dummy);

		/**
		 * this function MUST be called before calling readline() for the first
		 * time returns 0 if successful, -1 if not
		 * 
		 * @return
		 */
		public int using_history();

		/**
		 * this function returns the current history index
		 * 
		 * @return
		 */
		public int where_history();

		/**
		 * this function writes history to a text file; if filename is a NULL
		 * pointer, then %APPDATA%\.history is written returns 0 if successful,
		 * -1 or errno if not
		 * 
		 * @param filename
		 * @return
		 */
		public int write_history(String filename);
	}
}
