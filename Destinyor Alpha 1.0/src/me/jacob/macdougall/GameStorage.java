package me.jacob.macdougall;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GameStorage {
	
	private Map<String, Object> objects = new HashMap<>();
	private Map<Integer, String> names = new HashMap<>();
	
	public String name;
	public int amount = 0;;
	
	public GameStorage() {
		
	}
	
	public void putObject(String name, Object object) {
		objects.put(name, objects);
		names.put(amount, name);
		amount++;
	}
	
	public String getName(int NO) {
		return names.get(NO);
	}
	
	public Object getObject(String name) {
		return objects.get(name);
	}
	
	public Object getObject(int NO) {
		return objects.get(names.get(NO));
	}
	
	public Collection<Object> getValues() {
		return objects.values();
	}
	
	public Collection<String> getNames() {
		return names.values();
	}
	
	public int getLength() {
		return objects.size();
	}
	
	public int getNamesSize() {
		return names.size();
	}
	
}
