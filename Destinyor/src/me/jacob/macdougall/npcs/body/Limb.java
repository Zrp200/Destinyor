package me.jacob.macdougall.npcs.body;

import graphic.engine.screen.Bitmap;
import graphic.engine.screen.Screen;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Limb {
	
	public static Map<String, Limb> limbs = new HashMap<>();
	public static Map<Integer, String> names = new HashMap<>();
	
	public Bitmap sprite;
	
	String name;
	int no;
	public boolean renderable = false;
	
	public Limb(String name, boolean renderable) {
		this.name = name;
		this.renderable = renderable;
		this.no = getLength();
		limbs.put(name, this);
		names.put(no, name);
		if(getLength() > 1) {
			Limb[] limbs = {
				getLimb(no - 1), getLimb(no)	
			};
			
			Limbs.setLimbs(limbs);
		}
	}
	
	
	// Incase I want to do seperate rendering for bosses, or for enemy lose of limbs
	public void Sprite(Bitmap sprite) {
		this.sprite = sprite;
	}
	
	public void render(Screen screen, int x, int y) {
		if(renderable)
		screen.render(sprite, x, y);
	}
	
	public String getName() {
		return name;
	}
	
	public static int getNO(String name) {
		return limbs.get(name).no;
	}
	
	public static String getName(int NO) {
		return names.get(NO);
	}
	
	public static Limb getLimb(String name) {
		return limbs.get(name);
	}
	
	public static Limb getLimb(int NO) {
		return limbs.get(names.get(NO));
	}
	
	public static Collection<Limb> getValues() {
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
