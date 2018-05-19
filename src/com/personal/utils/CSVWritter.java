package com.personal.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVWritter {
	
	public static void writeCSVString(String csvString, String filename) throws IOException{
		Files.write(Paths.get(filename), csvString.getBytes());
	}
}
