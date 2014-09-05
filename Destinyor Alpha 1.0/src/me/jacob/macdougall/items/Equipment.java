package me.jacob.macdougall.items;

import graphic.engine.screen.Art;
import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;

import java.util.HashMap;
import java.util.Map;

import me.jacob.macdougall.magic.*;

public class Equipment extends Items implements Cloneable {
    
    // Resell Value = (Original Price - ((years of use * 100) + (missing part cost / 2))) / 2 
    
	
	public static Map<String, Equipment> equipment = new HashMap<>();
    public static Map<Integer, String> names = new HashMap<>();
	
     // 0 Head, 1 Body, 2 Left Shoulder, 3 Right Shoulder, 4 Left Hand, 5 Right Hand, 6 Belt, 7 Left Leg, 8 Right Leg, 9 Left Foot, 10 Right Foot
    	// 11 Neck, 12 Left Thumb, 13 Left Pointer, 14 Left Middle, 15 Left Ring, 16 Left Pinky, 17 Right Thumb, 18 Right Pointer, 19 Right Middle, 20 Right Ring, 21 Right Pinky
    	public static final int Head = 0, Body = 1, Shoulders = 2, Left_Hand = 3, Right_Hand = 4, Hands = 5, Belt = 6, Legs = 7, Feet = 8;
    	public static final int Neck = 9;
    	public static final int Left_Thumb = 10, Left_Pointer = 11, Left_Middle = 12, Left_Ring = 13, Left_Pinky = 14;
    	public static final int Right_Thumb = 15, Right_Pointer = 16, Right_Middle = 17, Right_Ring = 18, Right_Pinky = 19;
        
//        public static final int[] Type = {
//        	Head, Body, Shoulder, Left_Hand, Right_Hand, Belt, Legs, Feet, Neck, 
//        	Left_Thumb, Left_Pointer, Left_Middle, Left_Ring, Left_Pinky,
//        	Right_Thumb, Right_Pointer, Right_Middle, Right_Ring, Right_Pinky
//        };
        
        public static final String[] Stypes = {
            	"Head", "Body", "Shoulder", "Left_Hand", "Right_Hand", "Belt", "Legs", "Feet", "Neck", 
            	"Left_Thumb", "Left_Pointer", "Left_Middle", "Left_Ring", "Left_Pinky",
            	"Right_Thumb", "Right_Pointer", "Right_Middle", "Right_Ring", "Right_Pinky"
        };
        
	
	public int damage = 0;
	public Element element;
	public int type = 0;
    public Spells spellEffect;
    public int attribute = 0;
    public String attributeType = "";
    public boolean equipped = false;
    
    public boolean weapon = false;
    
	
	public Equipment(String name, int damage, int cost, Element element, int type, Spells spelleffect) {
		this.name = name;
		this.damage = damage;
		this.cost = cost;
		this.element = element;
		this.type = type;
                
                if(spelleffect != null) {
                    spelleffect = Spells.newInstance(spelleffect.name);
                    spelleffect.cost = 0;
                }
                this.spellEffect = spelleffect;
        this.equippable = true;
        names.put(equipment.size() - 1, name);
		put(name, this);
	}
	
	
	public Equipment(String name, int damage, int cost, Element element, String type, Spells spelleffect) {
		this.name = name;
		this.damage = damage;
		this.cost = cost;
		//this.attribute = attribute;
		this.element = element;
		this.equippable = true;
		for(int i = 0; i < Stypes.length; i++) {
			if(Stypes[i].equalsIgnoreCase(type)) {
				this.type = i;
			}
		}
                
                if(spelleffect != null) {
                    spelleffect = Spells.newInstance(spelleffect.name);
                    spelleffect.cost = 0;
                }
                this.spellEffect = spelleffect;
        
        names.put(equipment.size() - 1, name);
		put(name, this);
	}
	
	public static Equipment newStaticEquipmentInstance(String key) {
		try {
			return (Equipment) equipment.get(key).clone();
        } catch (Exception e) {
        	System.err.println("Unable to clone " + key);
        	return null;
        }
    }
	
	public Equipment newEquipmentInstance() {
		try {
			return (Equipment) equipment.get(this.name).clone();
        } catch (Exception e) {
        	System.err.println("Unable to clone " + this.name);
        	return null;
        }
    }
	
	
        public void render(Screen screen, int x, int y) {
            if(!name.equals("null")) {
            	//screen.render(Art.getSpritesheet()[0][0], 32, 32);
            GameFont.render(name, screen, x, y);
            GameFont.render(damage + ", " + cost, screen, (name.length() * 8) + x, y);
            }
        }
        
	public static Equipment get(String key) {
		return equipment.get(key);
	}
        
        public static Equipment[] getInventory() {
            return null;
        }
        
	public static void put(String key, Equipment equip) {
		equipment.put(key, equip);
		items.put(key, equip);
	}
	
	public boolean isWeapon() {
		if(type == Right_Hand) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isSheild() {
		if(type == Left_Hand) {
			return true;
		} else {
			return false;
		}
	}
	
//	public boolean isArmour()
//	if(type != Right_Hand) {
//		
//	}
	
//	public void equip(String key) {
//		items.get(key).equipped = true;
//		equipment.put(key, items.get(key));
//	}
//	
//	public void unEquip(String key) {
//		items.get(key).equipped = false;
//		equipment.remove(key);
//	}
	
}
