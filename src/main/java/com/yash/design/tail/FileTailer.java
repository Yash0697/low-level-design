package com.yash.design.tail;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

public class FileTailer implements Runnable {

	private File file;

	private final long SLEEP_TIME = 1000;

	private long position;
	private long lastModified;
	private boolean firstRead;

	private boolean isRunning;

	public FileTailer(File file) {
		this.file = file;
		Thread thread = new Thread(this);
//		thread.setDaemon(true);
		isRunning = true;
		firstRead = true;
		thread.start();
	}

	public File getFile() {
		return this.file;
	}

	public void stop() {
		isRunning = false;
	}

	@Override
	public void run() {
		RandomAccessFile raf = null;
		try {
			while (raf == null && isRunning) {
				raf = new RandomAccessFile(file, "r"); // read mode
				Thread.sleep(SLEEP_TIME);
				position = firstRead ? 0: file.length();
				lastModified = lastModified(file);
				raf.seek(position);
			}

			while (isRunning) {
				boolean isModified = lastModified(file) > lastModified;
				long currFileLength = file.length();
				if (currFileLength > position) {// continue reading the new appended content
					position = readAndSetPointer(raf);
					lastModified = lastModified(file);
				} else if (isModified && currFileLength <= position) {
					// this means everything was deleted from the file and re written. start reading
					// from
					// beginning
					position = 0;
					raf.seek(position);
					position = readAndSetPointer(raf);
					lastModified = lastModified(file);
				}
			}
			firstRead = false;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				if (raf != null)
					raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			stop();
		}
	}

	private long readAndSetPointer(RandomAccessFile raf) {
		long pos = 0;
		try {
			pos = raf.getFilePointer();
			long initialPos = pos;
			byte[] buffer = new byte[1024];
			int bytesRead;
			while (isRunning && (bytesRead = raf.read(buffer)) != -1) {
				// read each byte of byteBuffer array one by one and set position
				// we might be running FileTailer on different OS
				// different OS have different implementations of new line character
				// such as \r or \n,we are ignoring new line for now and reading as normal chars
				for (int i = 0; i < bytesRead; i++) {
					byte ch = buffer[i];
					System.out.print(new String((char)ch + ""));
					pos = (initialPos + i + 1);
				}
				initialPos = pos;
			}
			raf.seek(pos);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pos;
	}

	private long lastModified(File file) {
		try {
			return Files.getLastModifiedTime(file.toPath()).toMillis();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;

		}

	}
}

