package me.jacob.macdougall.items;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
	
	public Map<String, Equipment[]> items = new HashMap<>();
	public static Map<Integer, String> names = new HashMap<>();
	public static int numNames;
	
	public Inventory(String name, boolean Npc, Equipment[] items) {
		names.put(numNames, name);
		if(items != null) {
			this.items.put(names.get(numNames), items);
		}
		numNames += 1;
	}
	
	
	
}
