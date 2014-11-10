package me.jacob.macdougall.gui;

import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;

public class Commands extends GUI_Objects {

	String name;
	
	public Commands(String name, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.name = name;
	}
	
	public void render(Screen screen) {
		GameFont.render(name, screen, this.getX(), this.getY());
		
	}

}
