package me.jacob.macdougall.world;

import graphic.engine.screen.Bitmap;
import graphic.engine.screen.Screen;

import java.util.Map;
import java.util.HashMap;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.player.Player;

public class Staircase {
    
    private static Map<Integer, Staircase> staircases = new HashMap<>();
    //private static int amount;
    
//    private LevelMap GoToLevel;
//    private LevelMap StairsLocation;
    
    public Bitmap sprite;
    
    private int level;
    private int goTo;
    
    
    public int x, y;
    public int pX, pY;
    
//    public LevelMap GetLocation() {
//        return StairsLocation;
//    }
//    
//    public LevelMap SendPlayerTo() {
//        return GoToLevel;
//    }
    
    public void changeLevel(Destinyor game) {
    	if(Player.X == x && Player.Y == y && LevelMap.level == level) {
    		game.changeLevel(goTo);
    		Player.X = pX;
    		Player.Y = pY;
    	}
    }
    
    /**
     * Staircase that will lead to other levels (will also work for doors and entrances)
     * @param level what level this staircase is on
     * @param goTo the level the staircase moves the player to
     * @param x x location of the staircase
     * @param y y location of the staircase
     * @param x1 x location of where the player is placed
     * @param y1 y location of where the player is placed 
     * @param bitmap sprite of the staircase
     */
    public Staircase(int level, int goTo, int x ,int y, int x1, int y1, Bitmap bitmap) {
    	this.level = level;
    	this.goTo = goTo;
    	this.x = x;
    	this.y = y;
    	pX = x1;
    	pY = y1;
    	this.sprite = bitmap;
    	staircases.put(staircases.size(), this);
    }
    
    public void render(Screen screen) {
    	if(LevelMap.level == level)
    	screen.render(sprite, x, y);
    }
	
}
