package me.jacob.macdougall.world;

import graphic.engine.screen.Screen;

import java.util.*;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.graphics.Shadows;
import me.jacob.macdougall.player.Player;

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
	//public int[][] shadows;
	public int shadow = 0;
	
	public LevelMap() {
		tiles = new int[FloorWidth][FloorHeight];
		//shadows = tiles;
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
					if(!Tile.tiles[tiles[i][j]].solid) {
//						if(shadows[i][j] != 0) {
//							shadows[i][j] = 0;
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
								//shadow = Shadows.LEFT_UP_CORNER1;
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
					//shadows[i][j] = 0;
					Tile.tiles[tiles[i][j]].render(screen, MapX_Pos + i * SIZE, MapY_Pos + j * SIZE, shadow);
					shadow = 0;
				}
			}
		}
	}
	
//	public void checkShadows() {
//		Tile tile;
//		for(int i = 0; i < tiles.length; i++) {
//			for(int j = 0; j < tiles[i].length; j++) {
//				tile = getTileAt(i, j);
//				if(tile == null) {
//					shadows[i][j] = 0;
//					continue;
//				} else {
//					if(tile.isSolid()) {
//						shadows[i][j] = 0;
//						continue;
//					} else {
////						if(i * 32 >= (Player.X + 7) * 32 && j * 32 >= (Player.Y + 7) * 32 && j * 32<= (Player.Y + 7) * 32 && i * 32 <= (Player.X + 7) * 32) {
////							shadows[i][j] = 0;
////							continue;
////						}
//						//if(i-1 >= 0) {
//							
//						
//							//System.out.println(i);
//						if(getTileAt(i-1, j) != null && getTileAt(i-1, j).isSolid()) {
//							//tile.hasShadow = true;
//							shadows[i][j] = Shadows.LEFT;
//						}
//						if(Tile.tiles[tiles[i][j + 1]] != null && Tile.tiles[tiles[i][j + 1]].isSolid()) {
//							//tile.hasShadow = true;
//							shadows[i][j] = Shadows.DOWN;
//						}
//						//}
//					}
//				}
////				if(shadows[i][j] == Shadows.LEFT || shadows[i][j] == Shadows.DOWN) {
////					continue;
////				} else {
////				shadows[i][j] = 0;
////				}
//			}
//		}
	//}
	
	
}
