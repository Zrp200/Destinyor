package me.jacob.macdougall.world;

import java.util.HashMap;
import java.util.Map;

import me.jacob.macdougall.npcs.NPC;
import me.jacob.macdougall.npcs.ShopKeeper;
import me.jacob.macdougall.npcs.Traders;

public class Cities {
	
	public Map<String, NPC> npcs = new HashMap<>();
	public Map<String, ShopKeeper> stores = new HashMap<>();
	public Map<Integer, Houses> houses = new HashMap<>();
	public Map<String, Traders> traders = new HashMap<>();
	
	public int x, y;
	public String name;
	
	//public int width, height;
	
	public int level;
	public int gold;
	
	public Cities(String name, int x, int y, int gold) {
		
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
	
	
	
	
}
