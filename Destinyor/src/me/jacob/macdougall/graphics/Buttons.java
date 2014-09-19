package me.jacob.macdougall.graphics;

import graphic.engine.screen.Art;
import graphic.engine.screen.Bitmap;
import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;
import graphic.engine.window.Resolution;

public class Buttons {
	
	String name;
	
	public int x;
	public int y;
	public int width = 120;
	public int height = 20;
	
	Bitmap bitmap;
	
	public Buttons(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
		bitmap = Art.getButtons()[0][0];
	}
	
	public void render(Screen screen) {
		screen.render(bitmap, x, y);
		int x1 =  (50 - (name.length() * 2)) + x;
		GameFont.render(name, screen, x1, y + 6);
	}
	
	public int getAbsoluteX() {
		float xtemp = (float) Resolution.width() / 512;
		float X = (float) (x * xtemp);
		//X = x ^(Resolution.width() / Options.ResX[Resolution.getScaleX()] * Options.ResY[Resolution.getScaleY()]);
		return (int) X;
	}
	
	public int getAbsoluteY() {
		float ytemp = (float) Resolution.height() / 384;
		float Y = (float) (y * ytemp);
		//X = x ^(Resolution.width() / Options.ResX[Resolution.getScaleX()] * Options.ResY[Resolution.getScaleY()]);
		return (int) Y;
	}
	
	public int getWidth() {
		float widthtemp = (float) Resolution.width() / 512;
		float Width = (float) (width * widthtemp);
		return (int) Width;
	}
	
	public int getHeight() {
		float heighttemp = (float) Resolution.width() / 512;
		float Height = (float) (height * heighttemp);
		return (int) Height;
	}
	
	public boolean inBox(int x, int y) {
		int x1 = getAbsoluteX();
		int w1 = getWidth();
		int y1 = getAbsoluteY();
		int h1 = getHeight();
		if(x >= x1 && x <= x1 + w1) {
			if(y >= y1 && y <= y1 + h1) {
				return true;
			}
		}
		return false;
	}
	
	public void pressed() {
		bitmap = Art.getButtons()[1][0];
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
