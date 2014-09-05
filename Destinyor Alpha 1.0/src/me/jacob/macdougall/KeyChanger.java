package me.jacob.macdougall;

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
	
}
