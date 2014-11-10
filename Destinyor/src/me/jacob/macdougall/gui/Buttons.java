package me.jacob.macdougall.gui;

import graphic.engine.screen.Art;
import graphic.engine.screen.Bitmap;
import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;

public class Buttons extends GUI_Objects {
	
	String name;
	
//	public int x;
//	public int y;
//	public int width;
//	public int height;
	
	public boolean isScrollbar = false;
	
	protected Bitmap bitmap;
	
	public Buttons(String name, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.name = name;
		//this.x = x;
		//this.y = y;
		//this.width = width;
		//this.height = height;
		bitmap = Art.getButtons()[0][0];
	}
	
	public Buttons(String name, int x, int y) {
		super(x, y, 120, 20);
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = 120;
		this.height = 20;
		bitmap = Art.getButtons()[0][0];
	}
	
	public Buttons(String name, int x, int y, int width, int height, Bitmap bitmap) {
		super(x, y, width, height);
		this.name = name;
		this.bitmap = bitmap;
	}
	
	public void render(Screen screen) {
		screen.render(bitmap, x, y);
		int x1 =  (50 - (name.length() * 2)) + x;
		GameFont.render(name, screen, x1, y + 6);
	}
	
	public void pressed() {
		bitmap = Art.getButtons()[1][0];
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Bitmap getSprite() {
		return bitmap;
	}
	
}
