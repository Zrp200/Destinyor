package me.jacob.macdougall.graphics;

import me.jacob.macdougall.Destinyor;
import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;

public class Commands extends Buttons {

	public Commands(String name, int x, int y, int width, int height) {
		super(name, x, y);
		this.width = width;
		this.height = height;
	}
	
	public void render(Screen screen) {
		//screen.render(bitmap, x, y);
		//int x1 =  (50 - (name.length() * 2)) + x;
		if(Destinyor.Debug) {
			screen.render(bitmap, x, y);
		}
		GameFont.render(name, screen, x, y);
		
	}

}
