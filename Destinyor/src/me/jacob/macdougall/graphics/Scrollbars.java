package me.jacob.macdougall.graphics;

import graphic.engine.screen.Art;
import graphic.engine.screen.Bitmap;

public class Scrollbars extends Buttons {
	
	
	
	public int i = 0;
	
	public Scrollbars(int x, int y, int width, int height) {
		super("", x, y);
		this.width = width;
		this.height = height;
		isScrollbar = true;
		this.bitmap = Art.getScrollbars()[0][0];
	}
	
	public int clicked() {
		i++;
		
		return i;
	}

}
