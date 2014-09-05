package me.jacob.macdougall;

import input.engine.keyboard.Key;

public class Keys {
	
	// First class I used CONSTANTS for instead of constants
	
	private static final String LEFT = "Left";
	private static final String RIGHT = "Right";
	private static final String UP = "Up";
	private static final String DOWN = "Down";
	private static final String ESCAPE = "Escape";
	private static final String ATTACK = "Attack";
	private static final String HOME = "Home";
	private static final String PAGE_UP = "Page Up";
	private static final String PAGE_DOWN = "Page Down";
	private static final String ENTER = "Enter";
	
	
	public static boolean MoveLeft() {
		//boolean move = false;
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(LEFT)) {
				if(key.down) return true;
				//return (key.down) ? true : false;
			}
		}
		return false;
	}
	
	public static boolean MoveRight() {
		//boolean move = false;
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(RIGHT)) {
				if(key.down) return true;
				//return (key.down) ? true : false;
			}
		}
		return false;
	}
	
	public static boolean MoveUp() {
		//boolean move = false;
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(UP)) {
				if(key.down) return true;
				//return (key.down) ? true : false;
			}
		}
		return false;
	}
	
	public static boolean MoveDown() {
		//boolean move = false;
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(DOWN)) {
				if(key.down) return true;
				//return (key.down) ? true : false;
			}
		}
		return false;
	}
	
	public static boolean Escape() {
		//boolean move = false;
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(ESCAPE)) {
				if(key.down) return true;
				//return (key.down) ? true : false;
			}
		}
		return false;
	}
	
	public static boolean Attack() {
		//boolean move = false;
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(ATTACK)) {
				if(key.down) return true;
				//return (key.down) ? true : false;
			}
		}
		return false;
	}
	
	public static boolean Home() {
		//boolean move = false;
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(HOME)) {
				if(key.down) return true;
				//return (key.down) ? true : false;
			}
		}
		return false;
	}
	
	public static boolean PageUp() {
		//boolean move = false;
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(PAGE_UP)) {
				if(key.down) return true;
				//return (key.down) ? true : false;
			}
		}
		return false;
	}
	
	public static boolean PageDown() {
		//boolean move = false;
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(PAGE_DOWN)) {
				if(key.down) return true;
				//return (key.down) ? true : false;
			}
		}
		return false;
	}
	
	public static boolean Enter() {
		//boolean move = false;
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(ENTER)) {
				if(key.down) return true;
				//return (key.down) ? true : false;
			}
		}
		return false;
	}
	
	
}
