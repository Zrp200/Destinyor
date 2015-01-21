package me.jacob.macdougall.gui;

import java.awt.event.KeyEvent;

import input.engine.keyboard.Key;
import input.engine.mouse.Mouse;
import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;
import graphic.engine.screen.Bitmap;

public class TextBox extends GUI_Objects {

	public String input = "";
	public boolean focused = false;
	public Bitmap sprite;
	
	public TextBox(int x, int y, int width, int height, Bitmap sprite) {
		super(x, y, width, height);
		this.sprite = sprite;
	}
	
	public void render(Screen screen) {
		screen.render(sprite, x, y);
		GameFont.render(input, screen, x + 8, y + 6);
	}
	
	public void update(Mouse mouse) {
		if(mouse.pressed) {
			if(this.inBox(mouse.getPressed(Mouse.X), mouse.getPressed(Mouse.Y))) {
				focused = true;
			} else {
				focused = false;
			}
		}
		if(focused) {
			for(Key key : Key.keys.values()) {
				if(key.pressed) {
					switch(key.kevent) {
						case KeyEvent.VK_ENTER: focused = false; break;
						case KeyEvent.VK_BACK_SPACE: input = (String) input.subSequence(0, input.length() - 1); break;
						default: input += key.name; break;
					}
						
				}
				
			}
		}
	}
	
}
