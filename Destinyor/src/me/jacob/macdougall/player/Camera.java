package me.jacob.macdougall.player;

import me.jacob.macdougall.world.Tile;

public class Camera {
	
	public static int cX = Player.X * Tile.SIZE, cY = Player.Y * Tile.SIZE;
	public static int pX = Player.X;
	public static int pY = Player.Y;
	public static int mX = 0, mY = 0;
	//public static final int width = Resolution.width(), height = Resolution.height();
	
	public Camera() {
		
	}
	
}
