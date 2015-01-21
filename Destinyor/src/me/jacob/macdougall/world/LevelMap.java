package me.jacob.macdougall.world;

import graphic.engine.screen.Screen;

import java.util.*;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.graphics.Shadows;

public class LevelMap {
	
	public static Map<Integer, LevelMap> maps = new HashMap<>();
	
	public Map<Integer, Objects> objects = new HashMap<>();
	
	Destinyor game;
	public static int levelX = 1; // For miniMap
        public static int levelY = 1; // For MiniMap
        public static int level = 1; // For Level
	public static final int FloorWidth = 512;
	public static final int FloorHeight = 512;
	public int MapX_Pos;
	public int MapY_Pos;
	
	public static int SIZE = Tile.SIZE;
	
	public int floor;
	
	public int[][] tiles;
	
	public int shadow = 0;
	
	public LevelMap() {
		tiles = new int[FloorWidth][FloorHeight];
	}
	
	public Tile getTileAt(int i, int j) {
		if(i < 0 || i >= tiles.length || j < 0 || j > tiles.length) return null;
		return Tile.tiles[tiles[i][j]];
	}
	
	public void render(Screen screen, int x, int y) {
		int X = (int) (Screen.getWidth()  / 2.0);
		int Y = (int) (Screen.getHeight() / 2.0);
		//Destinyor.Refresh();
		MapX_Pos = x + X;
		MapY_Pos = y + Y;
		
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				if(Tile.tiles[tiles[i][j]] == null) {
					continue;
				} else {
					if(!Tile.tiles[tiles[i][j]].solid) {
							if(Tile.tiles[tiles[i - 1][j - 1]] != null && Tile.tiles[tiles[i - 1][j - 1]].solid) {
								shadow = Shadows.LEFT_CORNER;
								// Check the corners because nearly all shadow blocks will say yes, so you overwrite them after if the other conditions are true
							}
							if(Tile.tiles[tiles[i][j - 1]] != null && Tile.tiles[tiles[i][j - 1]].solid) {
								shadow = Shadows.DOWN;
							}
							if(Tile.tiles[tiles[i-1][j]] != null && Tile.tiles[tiles[i-1][j]].solid) {
								shadow = Shadows.LEFT;
							}
							if(Tile.tiles[tiles[i][j - 1]] != null && Tile.tiles[tiles[i][j - 1]].solid && Tile.tiles[tiles[i-1][j]] != null && Tile.tiles[tiles[i-1][j]].solid) {
								shadow = Shadows.LEFT_UP_CORNER;
							}
							
//							if(Tile.tiles[tiles[i][j + 1]] != null && Tile.tiles[tiles[i][j + 1]].solid) {
//								shadow = Shadows.LEFT_UP_CORNER2;
//							}
//						}
//						if(Tile.tiles[tiles[i][j]].hasShadow) {
//							shadow = Tile.tiles[tiles[i][j]].shadowInt;
//						}
						
						//if()
						
//						if(Tile.tiles[tiles[i+1][j]] != null && Tile.tiles[tiles[i+1][j]].solid) {
//							shadow = Shadows.RIGHT;
//						}
						
//						if(Tile.tiles[tiles[i][j-1]] != null && Tile.tiles[tiles[i][j-1]].solid) {
//							shadow = Shadows.UP;
//						}
						}
					Tile.tiles[tiles[i][j]].render(screen, MapX_Pos + i * SIZE, MapY_Pos + j * SIZE, shadow);
					//Objects.render(screen, MapX_Pos, MapY_Pos, getObjects());
					shadow = 0;
				}
			}
		}
	}
	
	public Objects[] getObjects() {
		Objects[] objects = new Objects[this.objects.size()];
		int o = 0;
		for(Objects object : this.objects.values()) {
			objects[o] = object;
			o++;
		}
		return objects;
	}
	
	public void putObjects(Objects object) {
		objects.put(object.x + object.y, object);
	}
	
	
	
}
