package me.jacob.macdougall.npcs;

import me.jacob.macdougall.world.Cities;
import me.jacob.macdougall.world.LevelMap;

public class Traders extends NPC {
	
	public Traders(String name, String frame, int x, int y, String dialouge, LevelMap map) {
		super(name, frame, x, y, dialouge, true, map);
		// TODO Auto-generated constructor stub
	}
	
	public Traders(String name, String frame, int x, int y, String dialouge) {
		super(name, frame, x, y, dialouge, true);
		// TODO Auto-generated constructor stub
	}

	public Cities city1;
	public Cities city2;
	
	public void tradeRoutes() {
		
	}
	
}
