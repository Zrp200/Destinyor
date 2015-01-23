package me.jacob.macdougall.input;

import input.engine.keyboard.InputHandler;
import input.engine.keyboard.Key;

public class KeyChanger {
	
	public static void changeKey(InputHandler input, int keyID) {
		for(Key key : Key.keys.values()) {
			if(key.kevent == keyID) {
				key.setKeyEvent(keyID);
			}
		}
	}
	
	public static void changeKey(int keyID, String effect) {
		for(Key key : Key.keys.values()) {
			if(key.kevent == keyID) {
				key.setEffect(effect);
			}
		}
	}
	
	public static void changeKey(Key key, String effect) {
		key.setEffect(effect);
	}
	
}
