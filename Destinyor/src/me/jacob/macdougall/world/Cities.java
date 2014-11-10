package me.jacob.macdougall.world;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.graphics.MassSprites;
import me.jacob.macdougall.npcs.NPC;
import me.jacob.macdougall.npcs.ShopKeeper;
import me.jacob.macdougall.npcs.Traders;
import me.jacob.macdougall.player.Player;

public class Cities extends MassSprites {
	
	
	// Add option to have single exit points or the whole map as a exit point
	
	public static Map<String, Cities> cities = new HashMap<>();
	
	public Map<String, NPC> npcs = new HashMap<>();
	public Map<String, ShopKeeper> stores = new HashMap<>();
	public Map<Integer, Houses> houses = new HashMap<>();
	public Map<String, Traders> traders = new HashMap<>();
	
	public int x, y;
	public String name;
	
	//public int width, height;
	
	public int level1;
	public int level2;
	public int gold;
	public Point size;
	public LevelMap map;
	
	
	/**
	 * 
	 * @param name
	 * @param x
	 * @param y
	 * @param gold
	 * @param x1 X1
	 * @param x2 X2
	 * @param y1 Y1
	 * @param y2 Y2
	 */
	public Cities(String name, int x, int y, int gold, int x1, int x2, int y1, int y2, int level1, int level2, int pX, int pY) {
		super(x, y, x1, x2, y1, y2, LevelMap.maps.get(level1));
		this.map = LevelMap.maps.get(level1);
		this.gold = gold;
		this.level1 = level1;
		this.level2 = level2;
	}
	
	public void goToTown(Destinyor game) {
		if(Player.X == x && Player.Y == y) {
			game.changeLevel(level2);
		}
	}
	
	public int getEconomy() {
		
		int economy = gold;
		
		if(!npcs.isEmpty())
		for(NPC n : npcs.values()) {
			economy += n.gold;
		}
		
		if(!stores.isEmpty())
		for(ShopKeeper n : stores.values()) {
			economy += n.gold;
		}
		
		if(!houses.isEmpty())
		for(Houses h : houses.values()) {
			economy += h.cost;
		}
		
		economy = economy / (npcs.size() + stores.size() + houses.size() + traders.size());
		
		return economy;
	}
	
	
//	public void render(Screen screen) {
//		for(int i = 0; i < frames[1] - frames[0]; i++)
//			for(int j = 0; j < frames[3] - frames[2]; j++)
//		screen.render(bitmap[i][j], map.MapX_Pos + (point.x + i) * LevelMap.SIZE, map.MapY_Pos + (point.y + j) * LevelMap.SIZE);
//	}
	
	
	
	
}
