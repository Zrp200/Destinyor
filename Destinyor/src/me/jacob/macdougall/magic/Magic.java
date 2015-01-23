package me.jacob.macdougall.magic;

import java.util.ArrayList;
import java.util.List;

public class Magic {
	
	public List<Magic> spells = new ArrayList<Magic>();
	
	public String name;
	
	public float damage;
	public float cost;
	public int AOE;
	
	/**
	 * 
	 * @param name
	 * @param damage
	 * @param cost
	 * @param AOE Area of effect, aka how many targets
	 */
	public Magic(String name, float damage, float cost, int AOE) {
		this.name = name;
		this.damage = damage;
		this.cost = cost;
		this.AOE = AOE;
	}
	
	public float getDamage() {
		return damage;
	}
	
	public float getCost() {
		return cost;
	}
	
	public int getAOE() {
		return AOE;
	}
	
	public boolean isAOE() {
		return (AOE > 1);
	}
	
}
