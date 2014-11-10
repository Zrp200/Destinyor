package me.jacob.macdougall.enemies;

import java.util.HashMap;
import java.util.Map;

import me.jacob.macdougall.GameVariables;
import me.jacob.macdougall.items.Equipment;
import me.jacob.macdougall.magic.Spells;
import me.jacob.macdougall.npcs.body.Entities;
import me.jacob.macdougall.npcs.body.Limb;
import me.jacob.macdougall.world.LevelMap;

public class Dummy {
	
	public Map<Integer, Equipment> equipped = new HashMap<>();
    public Map<Integer, Spells> spells = new HashMap<>();
    
	LevelMap level;
	
	protected String name;
	protected String gender;
	
	protected int lvl;
	protected int exp;
	protected int hp;
	protected int str;
	protected int skl;
	protected int spd;
	protected int luk;
	protected int def;
	protected int wis;
	protected int gold;
	protected int x, y;
	
	protected int limit = 500;
	protected int turnTimer = 0;
	
	public boolean npc = true;
	
	private boolean paused = true;
	
	private Entities entity;
	
	/**
	 * 
	 * @param name
	 * @param gender
	 * @param lvl
	 * @param exp
	 * @param hp
	 * @param str
	 * @param skl
	 * @param spd
	 * @param luk
	 * @param def
	 * @param wis
	 * @param gold
	 * @param x
	 * @param y
	 * @param spells
	 * @param equipment
	 */
	public Dummy(String name, String gender, int lvl, int exp, int hp, int str, int skl, int spd, int luk, int def, int wis, int gold, int x, int y, Spells[] spells, Equipment[] equipment) {
		this.name = name;
		this.gender = gender;
		this.lvl = lvl;
		this.exp = exp;
		this.hp = hp;
		this.str = str;
		this.skl = skl;
		this.spd = spd;
		this.luk = luk;
		this.def = def;
		this.wis = wis;
		this.gold = gold;
		this.x = x;
		this.y = y;
		if(gender != null) {
			npc = false; // Is player if it has a gender
		}
		if(spells != null && spells.length > 0) {
			for(int i = 0; i < spells.length; i++) {
				this.spells.put(i, spells[i]);
			}
		}
		if(equipment != null && equipment.length > 0) {
			for(int i = 0; i < equipment.length; i++) {
				this.equipped.put(i, equipment[i]);
			}
		}
	}
	
	/**
	 * http://stackoverflow.com/questions/5802118/why-we-use-clone-method-in-java
	 * <br>
	 * Clone constructor
	 * @param dummy
	 */
	protected Dummy(Dummy dummy) {
		this.name = dummy.name;
		this.gender = dummy.gender;
		this.lvl = dummy.lvl;
		this.exp = dummy.exp;
		this.hp = dummy.hp;
		this.str = dummy.str;
		this.skl = dummy.skl;
		this.spd = dummy.spd;
		this.luk = dummy.luk;
		this.def = dummy.def;
		this.wis = dummy.wis;
		this.gold = dummy.gold;
		this.x = dummy.x;
		this.y = dummy.y;
		this.npc = dummy.npc;
		this.spells = dummy.spells;
		this.equipped = dummy.equipped;
		this.level = dummy.level;
		this.entity = dummy.entity;
		this.limit = dummy.limit;
	}
	
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public int getHp() {
		int HP = this.hp;
		if(GameVariables.getDifficultly() == GameVariables.Difficultly.EASY) {
			if(this.npc) {
				HP = (int) (HP * 0.50f);
			} else {
				HP = HP * 2;
			}
		}
		return HP;
	}
	
	public int getTrueHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getStr() {
		return str;
	}
	
	public void setStr(int str) {
		this.str = str;
	}
	
	public int getSkl() {
		return skl;
	}
	
	public void setSkl(int skl) {
		this.skl = skl;
	}
	
	public int getSpd() {
		return spd;
	}
	
	public void setSpd(int spd) {
		this.spd = spd;
	}
	
	public int getLuk() {
		return luk;
	}
	
	public void setLuk(int luk) {
		this.luk = luk;
	}
	
	public int getDef() {
		return def;
	}
	
	public void setDef(int def) {
		this.def = def;
	}
	
	public int getWis() {
		return wis;
	}
	
	public void setWis(int wis) {
		this.wis = wis;
	}
	
	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return the gender if it is a player, otherwise returns an empty string
	 */
	public String getGender() {
		if(npc) {
			return "";
		} else {
			return gender;
		}
	}
	
	public void setXandY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setLevel(LevelMap level) {
		this.level = level;
	}
	
	public void setLevel(int level) {
		this.level = LevelMap.maps.get(level);
	}
	
	public boolean alive() {
            return (getHp() > 0);
	}
	
	public boolean hasEquipment() {
		return !this.equipped.isEmpty();
	}
	
	public Equipment[] armour() {
		Equipment[] e = new Equipment[equipped.size()];
		int i = 0;
		for(Equipment equip : equipped.values()) {
			if(equip.isArmour()) {
				e[i] = equip;
				i++;
			}
		}
		return e;
	}
	
	/**
	 * Returns an array containing all armours, no nulls or weapons.
	 * @return Returns any equipment that is equipped, that is a armour. (Defined by Equipment.isArmour())
	 */
	public Equipment[] returnArmour() {
		Equipment[] equip = this.armour();
		Equipment[] e = new Equipment[equip.length];
		int j = 0;
		if(equip != null && equip.length > 0 && equip[0] != null) {
			for(int i = 0; i < equip.length; i++) {
				if(equip[i] != null) {
					e[i] = equip[i];
					j++;
				}
			}
			equip = new Equipment[j];
			j = 0;
			for(Equipment equipment : e) {
				if(equipment != null) {
				equip[j] = equipment;
				j++;
				}
			}
		}
		return equip;
	}
	
	/**
	 * Gets all equipment, creates null spaces where there a weapon doesn't exist, however there still is equipment
	 * @return
	 */
	public Equipment[] weapon() {
		Equipment[] e = new Equipment[equipped.size()]; // Only two hands
		int i = 0;
		for(Equipment equip : equipped.values()) {
			if(equip.isWeapon()) {
				e[i] = equip;
				i++;
			}
		}
		return e;
	}
	
	/**
	 * Returns an array containing all weapons, no nulls or armours.
	 * @return Returns any equipment that is equipped, that is a weapon. (Defined by Equipment.isWeapon())
	 */
	public Equipment[] returnWeapons() {
		Equipment[] equip = this.weapon();
		Equipment[] e = new Equipment[equip.length];
		int j = 0;
		if(equip != null && equip[0] != null) {
			for(int i = 0; i < equip.length; i++) {
				if(equip[i] != null) {
					e[i] = equip[i];
					j++;
				}
			}
			equip = new Equipment[j];
			j = 0;
			for(Equipment equipment : e) {
				if(equipment != null) {
				equip[j] = equipment;
				j++;
				}
			}
		}
		return equip;
	}
	
	/**
	 * Uses advance return
	 * @return if(ta >= limit) return true, else return false
	 */
	public boolean canAttack() {
            return (turnTimer >= limit && hp > 0);
	}
	
	/**
	 * Continues the turn timer
	 */
	public void play() {
		if(!paused)
			turnTimer++;
		paused = false;
	}
	
	/**
	 * Pauses the turn timer
	 */
	public void pause() {
		paused = true;
	}
	
	public int getTurnTimer() {
		return turnTimer;
	}
	
	public int getLimit() {
		return limit;
	}
	
	public void resetTurnTimer() {
		turnTimer = 0;
	}
	
	public Entities getEntityType() {
		return entity;
	}
	
	public Limb[] getLimbs() {
		return (Limb[]) entity.limbs.toArray();
	}
	
	public static Dummy newInstance(Dummy dummy) {
		Dummy d = new Dummy(dummy);
		
		return d;
	}
}
