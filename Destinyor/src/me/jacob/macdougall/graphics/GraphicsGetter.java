package me.jacob.macdougall.graphics;

import graphic.engine.window.Resolution;

public class GraphicsGetter {
	
	
	
	public static int getAbsoluteX(int x) {
		float xtemp = (float) Resolution.width() / 512;
		float X = (float) (x * xtemp);
		return (int) X;
	}
	
	public static int getAbsoluteY(int y) {
		float ytemp = (float) Resolution.height() / 384;
		float Y = (float) (y * ytemp);
		return (int) Y;
	}
	
	public static int getWidth(int width) {
		float widthtemp = (float) Resolution.width() / 512;
		float Width = (float) (width * widthtemp);
		return (int) Width;
	}
	
	public static int getHeight(int height) {
		float heighttemp = (float) Resolution.width() / 512;
		float Height = (float) (height * heighttemp);
		return (int) Height;
	}
	
}
