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
	
	private static final String ONE = "1";
	private static final String TWO = "2";
	private static final String THREE = "3";
	private static final String FOUR = "4";
	private static final String FIVE = "5";
	
	private static final String ENEMY = "Enemy";
	
	private static final String INVENTORY = "Inventory";
	private static final String EQUIPMENT = "Equipment";
	private static final String MINIMAP = "Minimap";
	
	public static boolean MoveLeft() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(LEFT)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean MoveRight() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(RIGHT)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean MoveUp() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(UP)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean MoveDown() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(DOWN)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean Escape() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(ESCAPE)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean Attack() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(ATTACK)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean Home() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(HOME)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean PageUp() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(PAGE_UP)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean PageDown() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(PAGE_DOWN)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean Enter() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(ENTER)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean Enemy1() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(ENEMY + ONE)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean Enemy2() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(ENEMY + TWO)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean Enemy3() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(ENEMY + THREE)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean Enemy4() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(ENEMY + FOUR)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean Enemy5() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(ENEMY + FIVE)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean Inventory() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(INVENTORY)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean Equipment() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(EQUIPMENT)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
	public static boolean Minimap() {
		for(Key key : Key.keys.values()) {
			if(key.getEffect().equals(MINIMAP)) {
				if(key.down) return true;
			}
		}
		return false;
	}
	
}
