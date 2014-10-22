package me.jacob.macdougall.player;

import graphic.engine.screen.Art;
import graphic.engine.screen.Bitmap;

import java.io.File;
import java.net.URL;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.files.DestinyorFiles;

public class SpriteChecker {
	//public static final URL url = Destinyor.class.getResource(DestinyorFiles.DestinyorCharacter1Sheet);
	//public static final File file = new File(url.toString());
	
	//public static final boolean exists = file.exists();
	
	
	//public static final Bitmap[][] sprite = (!exists) ? Art.getSpritesheet() : Art.cut(32, 32, DestinyorFiles.DestinyorCharacter1Sheet);
	public static final boolean exists = new File(DestinyorFiles.DestinyorCharacter1Sheet).exists();
	public static final Bitmap[][] sprite = (exists) ? Art.cut(32, 32, DestinyorFiles.DestinyorCharacter1Sheet) : Art.cut(DestinyorFiles.DestinyorCharacter1Sheet, 32, 32, Destinyor.class);
//	public static final Bitmap[][] frames = 
//		{
//			{ Art.getSpritesheet()[14][15], Art.getSpritesheet()[15][15] }, // Up
//			{ Art.getSpritesheet()[14][12], Art.getSpritesheet()[15][12] }, // Down
//			{ Art.getSpritesheet()[14][13], Art.getSpritesheet()[15][13] }, // Left
//			{ Art.getSpritesheet()[14][14], Art.getSpritesheet()[15][14] } // Right
//		};
    //private static void getPlayerSprites(Destinyor game) {
    	//sprite = 
    //}
	
	public static final Bitmap[][] frame1 = 	
	{
		{ sprite[0][3],  sprite[1][3], sprite[2][3], sprite[3][3]}, // Up
		{ sprite[0][0],  sprite[1][0], sprite[2][0], sprite[3][0] }, // Down
		{ sprite[0][1],  sprite[1][1], sprite[2][1], sprite[3][1] }, // Left
		{ sprite[0][2],  sprite[1][2], sprite[2][2], sprite[3][2] } // Right
	};
	
//	public static final Bitmap[][] frame2 = 
//	{
//		{ sprite[14][15], sprite[15][15] }, // Up
//		{ sprite[14][12], sprite[15][12] }, // Down
//		{ sprite[14][13], sprite[15][13] }, // Left
//		{ sprite[14][14], sprite[15][14] } // Right
//	};
	
	//public static final Bitmap[][] frames = (exists) ? frame1 : frame2;
	public static final Bitmap[][] frames = frame1;
	
	
	
}
