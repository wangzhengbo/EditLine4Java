package cn.com.editline;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.com.tcc.State;
import cn.com.tcc.TCC;

import com.sun.jna.Function;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;

public class EditLineImpl2 extends AbstractEditLine {
	private final State state;
	private final Map<String, Function> functions = new HashMap<String, Function>();

	public EditLineImpl2() throws Exception {
		this("tcc", "wineditline");
	}

	public EditLineImpl2(String tccPath, String winEditLinePath)
			throws Exception {
		TCC.init(tccPath);
		state = new State();
		String sharedLibPath = new File(tccPath, "win32/"
				+ (Platform.is64Bit() ? "x86-64" : "x86")).getAbsolutePath();

		addIncludePath(winEditLinePath);
		addIncludePath(new File(winEditLinePath, "editline").getAbsolutePath());
		addFile(new File(sharedLibPath, "lib/user32.def").getAbsolutePath());
		addFile(new File(winEditLinePath, "fn_complete.c").getAbsolutePath());
		addFile(new File(winEditLinePath, "history.c").getAbsolutePath());
		addFile(new File(winEditLinePath, "editline.c").getAbsolutePath());

		if (!state.relocateAuto()) {
			throw new Exception(String.format("Unable to add file."));
		}
	}

	protected String add_history(String line) {
		return invokeString("add_history", line);
	}

	protected void clear_history() {
		invoke("clear_history");
	}

	@Override
	public void close() {
		state.delete();
	}

	protected HIST_ENTRY current_history() {
		return invokeHistEntry("current_history");
	}

	protected void free_history() {
		invoke("free_history");
	}

	protected HIST_ENTRY history_get(int index) {
		return invokeHistEntry("history_get", index);
	}

	protected int history_length() {
		return invokeInt("history_length");
	}

	protected int history_set_pos(int index) {
		return invokeInt("history_set_pos", index);
	}

	protected HIST_ENTRY next_history() {
		return invokeHistEntry("next_history");
	}

	protected HIST_ENTRY previous_history() {
		return invokeHistEntry("previous_history");
	}

	protected Pointer rl_attempted_completion_function() {
		return state.getSymbol("rl_attempted_completion_function");
	}

	protected String[] rl_completion_matches(String text,
			CompletionEntryFunction completionEntryFunction) {
		Pointer pointer = invokePointer("rl_completion_matches", text,
				completionEntryFunction);
		return (pointer == null) ? null : pointer.getStringArray(0);
	}

	protected String readline_(String prompt) {
		return invokeString("readline", prompt);
	}

	protected HIST_ENTRY remove_history(int index) {
		return invokeHistEntry("remove_history", index);
	}

	protected HIST_ENTRY replace_history_entry(int index, String line,
			Pointer dummy) {
		return invokeHistEntry("replace_history_entry", index, line, dummy);
	}

	protected int using_history() {
		return invokeInt("using_history");
	}

	protected int where_history() {
		return invokeInt("where_history");
	}

	protected int write_history(String file) {
		return invokeInt("write_history", file);
	}

	private void addIncludePath(String includePath) throws Exception {
		File file = new File(includePath);
		if (!state.addIncludePath(file.getAbsolutePath())) {
			throw new Exception(String.format(
					"Unable to add include path '%s'.", file.getAbsolutePath()));
		}
	}

	private void addFile(String filePath) throws Exception {
		File file = new File(filePath);
		if (!state.addFile(file.getAbsolutePath())) {
			throw new Exception(String.format("Unable to add file '%s'.",
					file.getAbsolutePath()));
		}
	}

	private Function getFunction(String funcName) {
		Function func = functions.get(funcName);
		if (func == null) {
			func = state.getFunction(funcName);
			functions.put(funcName, func);
		}
		return func;
	}

	private void invoke(String funcName, Object... args) {
		getFunction(funcName).invoke(args);
	}

	private Object invoke(String funcName, Class<?> returnType, Object... args) {
		return getFunction(funcName).invoke(returnType, args);
	}

	private int invokeInt(String funcName, Object... args) {
		return getFunction(funcName).invokeInt(args);
	}

	private String invokeString(String funcName, Object... args) {
		return invokeString(funcName, false, args);
	}

	private String invokeString(String funcName, boolean wide, Object... args) {
		return getFunction(funcName).invokeString(args, wide);
	}

	private Pointer invokePointer(String funcName, Object... args) {
		return getFunction(funcName).invokePointer(args);
	}

	private HIST_ENTRY invokeHistEntry(String funcName, Object... args) {
		return (HIST_ENTRY) invoke(funcName, HIST_ENTRY.class, args);
	}
}
