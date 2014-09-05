package me.jacob.macdougall;

import java.util.ArrayList;
import java.util.List;

public class DebugWriter {
	
	public static int w = 0;
	
	public static List<String> strings = new ArrayList<String>();
	
	public static void println(String args) {
		strings.add(args);
	}
	
	public static void removeln(String args) {
		strings.remove(args);
	}
	
	public static String getln(int key) {
		return strings.get(key);
	}
	
}
