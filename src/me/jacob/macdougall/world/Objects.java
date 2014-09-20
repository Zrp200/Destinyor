package me.jacob.macdougall.world;

import java.util.HashMap;
import java.util.Map;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.Time;
import graphic.engine.screen.Art;
import graphic.engine.screen.Bitmap;
import graphic.engine.screen.Screen;

public class Objects {
	
	/*
	 * Class that will handle none tile set objects
	 * Such as trees, tables, chairs ect
	 */
	
	public static Map<String, Objects> objects = new HashMap<>();
	
	boolean animated;
	public String name;
	public int x;
	public int y;
	int x1, y1;
	int f1, f2;
	int[] frames;
	
	public Objects(String name, int x, int y, boolean animated, String frame) {
		frame = frame.trim();
		String[] frames = frame.split(",");
		this.frames = new int[frames.length];
		for(int i = 0; i < frames.length; i++) {
			this.frames[i] = Integer.parseInt(frames[i].trim());
		}
		this.x = x;
		this.y = y;
		x1 = this.frames[0];
		f1 = this.frames[0];
		y1 = this.frames[1];
		f2 = this.frames[this.frames.length - 1];
		this.animated = animated;
		objects.put(name, this);
	}
	
	public Bitmap sprite() {
		if(animated) {
			if(Time.getObjectTimer(30, false))
			x1++;
			if(x1 > f2) {
				x1 = f1;
			}
			return Art.getSpritesheet()[x1][y1];
		} else {
			return Art.getSpritesheet()[frames[0]][frames[1]];
		}
	}
	
	public void render(LevelMap map, Screen screen) {
		//System.out.println("This");
		//Tile.tiles[map.tiles[x][y]].render(screen, map.MapX_Pos + x * LevelMap.SIZE, map.MapY_Pos + y * LevelMap.SIZE, null, this);
		//Tile.tiles[map.tiles[x][y]].render(screen, map.MapX_Pos + x * LevelMap.SIZE, map.MapY_Pos + y * LevelMap.SIZE, null, this, 0);
		screen.render(sprite(), map.MapX_Pos + x * 32, map.MapY_Pos + y * 32);
	}
	
	
}
