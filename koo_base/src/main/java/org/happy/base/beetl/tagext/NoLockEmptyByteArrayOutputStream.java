package org.happy.base.beetl.tagext;

import java.io.OutputStream;

public class NoLockEmptyByteArrayOutputStream extends OutputStream {

	public NoLockEmptyByteArrayOutputStream() {

	}

	public NoLockEmptyByteArrayOutputStream(int size) {

	}

	public void write(int b) {

	}

	public void write(byte b[], int off, int len) {

	}

	public void reset() {

	}

	public byte toByteArray()[] {
		return new byte[0];
	}

	public int size() {
		return 0;
	}

}