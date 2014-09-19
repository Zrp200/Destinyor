package me.jacob.macdougall.magic;

import java.util.HashMap;
import java.util.Map;

import me.jacob.macdougall.npcs.Enemy;
import me.jacob.macdougall.player.Player;

public class Spells implements Cloneable {
	
	/*
	 * Makes spells have their own folder with each file being an entities spell list, like dialouges
	 */
	
	public static Map<String, Spells> spells = new HashMap<>();
        public static Map<Integer, String> names = new HashMap<>();
        
        public static Spells Attack = new Spells("Attack", Element.get(Element.Physical), 0, 1, 0, "0");
	
	public Element element;
	public String name;
	public int damage;
	public int targets;
	public int cost;
        public boolean targeted = false;
        public boolean UseAbleOutsideCombat = false;
        public int effect = Normal;
        public boolean inUse = false;

	public int[] condition;
	
	//																																										// Heals, Statboosts
	public static final int Normal = 0, Requires_Sword = 1, Requires_Shield = 2, Requires_Combo = 3, Requires_Two_Hands = 4, UseAbleOutside = 5, AoE = 6, Ignore_Defense = 7, Helpful = 8;
	
	// 0 normal spell, 1 requires sword, 2 requires sheild, 3 requires combo, 4 reuqires two handed, 
	int conditions = 0;
	
	
	public Spells(String name, Element element, int damage,  int targets, int cost, String Condition) {
		System.out.println(name);
		this.name = name;
		this.element = element;
		this.damage = damage;
		this.targets = targets;
		this.cost = cost;
		
		if(Condition.contains(",")) {
		String[] temp = Condition.split(",");
		condition = new int[temp.length];
		for(int i = 0; i < temp.length; i++) {
			this.condition[i] = Integer.parseInt(temp[i]);
		}
		} else {
			condition = new int[1];
			condition[0] = Integer.parseInt(Condition);
		}
		
                put(name, this);
                names.put(spells.size() + 1, name);
                //System.out.println(name);
	}
	
	public static void put(String name, Spells spells) {
		Spells.spells.put(name, spells);
	}
	
	public static Spells get(String name) {
		return spells.get(name);
	}
	
	public static Spells newInstance(String key){
		try {
			return (Spells) spells.get(key).clone();
		} catch (CloneNotSupportedException e) {
			System.err.println("Unable to clone " + key);
			return null;
		}
	}
	
	public int getCombo() {
		return 0;
	}
        
        public void attack(Player player, Enemy enemy) {
//            if(entity.Resistance.getElement() != null) {
//                if(entity.Resistance.getElement().equals(this.element.getElement())) {
//                    entity.HP = -((this.damage * player.Wis) / 2);
//                } else {
//                    if(entity.Resistance.getWeakness().equals(this.element.getElement()))
//                        entity.HP = -((this.damage * player.Wis) * 2);
//                    else
//                        entity.HP = -(this.damage * player.Wis);
//                }
//            } else {
            enemy.HP -= ((this.damage * player.Wis));
            //player.mana = -this.cost;
           // }
        }
	
}