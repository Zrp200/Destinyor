package me.jacob.macdougall.npcs;

import graphic.engine.screen.Bitmap;
import graphic.engine.screen.Screen;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Limbs {
	
	public static Map<String, Limbs> limbs = new HashMap<>();
	public static Map<Integer, String> names = new HashMap<>();
	
	public Bitmap sprite;
	
	String name;
	int no;
	public boolean renderable = false;
	
	public Limbs(String name, int no, boolean renderable) {
		this.name = name;
		this.no = no;
		this.renderable = renderable;
		limbs.put(name, this);
		names.put(no, name);
	}
	
	
	// Incase I want to do seperate rendering for bosses, or for enemy lose of limbs
	public void Sprite(Bitmap sprite) {
		this.sprite = sprite;
	}
	
	public void render(Screen screen, int x, int y) {
		if(renderable)
		screen.render(sprite, x, y);
	}
	
	public static int getNO(String name) {
		return limbs.get(name).no;
	}
	
	public static String getName(int NO) {
		return names.get(NO);
	}
	
	public static Limbs getLimb(String name) {
		return limbs.get(name);
	}
	
	public static Limbs getLimb(int NO) {
		return limbs.get(names.get(NO));
	}
	
	public static Collection<Limbs> getValues() {
		return limbs.values();
	}
	
	public static Collection<String> getNames() {
		return names.values();
	}
	
	public static int getLength() {
		return limbs.size();
	}
	
	public static int getNamesSize() {
		return names.size();
	}
}
