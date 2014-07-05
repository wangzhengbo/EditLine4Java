package com.sun.jna;

public class CallbackReferenceHelper {
	public static Pointer getFunctionPointer(Callback cb) {
		return CallbackReference.getFunctionPointer(cb);
	}
}
