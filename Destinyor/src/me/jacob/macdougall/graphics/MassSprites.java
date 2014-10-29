package me.jacob.macdougall.graphics;

import java.awt.Point;

import me.jacob.macdougall.world.LevelMap;

import graphic.engine.screen.Art;
import graphic.engine.screen.Bitmap;
import graphic.engine.screen.Screen;

/**
 * class that handles classes with more than one tile of sprite
 * @author Jacob
 *
 */
public class MassSprites {
	
	public Bitmap[][] bitmap;
	public Point point;
	public int[] frames = new int[4];
	public LevelMap map;
	
	
	public MassSprites(int x, int y, int x1, int x2, int y1, int y2, LevelMap map) {
		point = new Point(x, y);
		frames[0] = x1;
		frames[1] = x2;
		frames[2] = y1;
		frames[3] = y2;
		
		bitmap = new Bitmap[(x2 - x1)][(y2 - y1)];
		
		for(int i = 0; i < (x2 - x1); i++) { // Gets the size of the x co-ord
			for(int j = 0; j < (y2 - y1); j++) { // Gets the size of the y co-ord
				bitmap[i][j] = Art.getSpritesheet()[frames[0] + i][frames[2] + j];
			}
		}
		this.map = map;
	}
	
	public void render(Screen screen) {
		if(map != null) {
		for(int i = 0; i < frames[1] - frames[0]; i++)
			for(int j = 0; j < frames[3] - frames[2]; j++)
		screen.render(bitmap[i][j], map.MapX_Pos + (point.x + i) * LevelMap.SIZE, map.MapY_Pos + (point.y + j) * LevelMap.SIZE);
		}
	}
	
}
