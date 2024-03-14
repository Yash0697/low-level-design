package com.yash.design.tail;

import java.io.File;
import java.nio.file.Paths;

public class FileTailerTest {
	public static void main(String[] args) {
		final String SEP = File.separator;
		String filePath = null;
		if(args.length < 2) 
			filePath = "C:"+SEP+"Users"+SEP+"Perennial"+SEP+"Downloads"+SEP+"jarvis-basic-logs 906001001.txt";
		else {
			if(args[1].indexOf(SEP) == -1) {
				filePath = args[1];
				filePath = Paths.get("").toAbsolutePath().toString();
				filePath = filePath + SEP + args[1];
			}
		}

		File file = new File(filePath);
		FileTailer ft = new FileTailer(file);
	}
}