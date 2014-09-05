package me.jacob.macdougall.world;

import graphic.engine.screen.Screen;

import java.util.*;

import me.jacob.macdougall.Destinyor;

public class LevelMap {
	
	public static HashMap<Integer, LevelMap> Maps = new HashMap<>();
	
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
	
	public LevelMap() {
		tiles = new int[FloorWidth][FloorHeight];
	}
	
	public Tile getTileAt(int i, int j) {
		if(i < 0 || i >= tiles.length || j < 0 || j > tiles.length) return null;
		return Tile.tiles[tiles[i][j]];
	}
	
	public void render(Screen screen, int x, int y) {
		int X = 512 / 2;
		int Y = 768 / 4;
		
		MapX_Pos = x + X;
		MapY_Pos = y + Y;
		
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				if(Tile.tiles[tiles[i][j]] == null) {
					continue;
				} else {
					Tile.tiles[tiles[i][j]].render(screen,  MapX_Pos + i * SIZE, MapY_Pos + j * SIZE, null);
				}
			}
		}
	}
	
	
}
