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
	
	private boolean hasEquipment = false;
	
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
	
	/**
	 * http://stackoverflow.com/questions/5802118/why-we-use-clone-method-in-java
	 * <br>
	 * Clone constructor
	 * @param limb Limb that will be copied
	 */
	private Limb(Limb limb) {
		this.name = limb.name;
		this.renderable = limb.renderable;
		this.no = limb.no;
	}
	
	/**
	 * Incase I want to do seperate rendering for bosses, or for enemy lose of limbs
	 * @param sprite
	 */
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
	
	/**
	 * Is equipment attached to this instance of the limb?
	 * @return
	 */
	public boolean isEquipped() {
		return hasEquipment;
	}
	
	/**
	 * Is equipment attached to this instance of the limb?
	 * @param equipped
	 */
	public void setEquipped(boolean equipped) {
		hasEquipment = equipped;
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
	
	public static Limb newInstance(String name) {
		Limb limb = new Limb(limbs.get(name));
		return limb;
	}
	
	public static Limb newInstance(Limb l) {
		Limb limb = new Limb(l);
		return limb;
	}
	
	public Limb newInstance() {
		Limb limb = new Limb(this);
		return limb;
	}
	
	
	
	
	
}
