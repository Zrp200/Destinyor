package me.jacob.macdougall.items;

import graphic.engine.screen.Art;
import graphic.engine.screen.Bitmap;

import java.util.HashMap;
import java.util.Map;

public class Items implements Cloneable {
	
	public static Map<String, Items> items = new HashMap<>();
	
	public String name = "";
	public int cost = 0;
	
	public Bitmap frame = Art.getSpritesheet()[0][0];
	
	public boolean equippable = false;
	
	
	public static void addToInventory(String key) {
		
	}
	
	public static void removeFromInventory(String key) {
		
	}
	
	public static Items newStaticInstance(String key) {
		try {
			return (Equipment) items.get(key).clone();
        } catch (CloneNotSupportedException e) {
        	System.err.println("Unable to clone " + key);
        	return null;
        }
    }
	
	public Items newInstance() {
		try {
			return (Equipment) items.get(this.name).clone();
        } catch (CloneNotSupportedException e) {
        	System.err.println("Unable to clone " + this.name);
        	return null;
        }
    }
	
	public void getColor() {
		double pixel = 0;
		int[] pixels = frame.pixels;
		
		for(int i = 0; i < frame.pixels.length; i++) {
			pixel += pixels[i];
		//System.out.println(pixels[i]);
		}
		pixel = pixel / pixels.length;
		System.out.println(pixel);
	}
}
